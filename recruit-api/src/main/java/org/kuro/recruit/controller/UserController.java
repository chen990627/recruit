package org.kuro.recruit.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kuro.recruit.config.Constant;
import org.kuro.recruit.model.bo.LoginFromBo;
import org.kuro.recruit.model.bo.MobileBo;
import org.kuro.recruit.model.entity.LoginLog;
import org.kuro.recruit.model.entity.User;
import org.kuro.recruit.model.result.Result;
import org.kuro.recruit.model.result.ResultCode;
import org.kuro.recruit.service.LoginLogService;
import org.kuro.recruit.service.UserService;
import org.kuro.recruit.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sys")
@Api(value = "用户模块", tags = "用户相关")
public class UserController {

    @Autowired
    public RedisOperator __redis;

    @Autowired
    private SMSUtils __sms_utils;

    @Autowired
    private UserService __user_service;

    @Autowired
    private LoginLogService __login_log_service;


    @ApiOperation(value = "短信验证码", notes = "获取短信验证码")
    @PostMapping("/sms")
    public Result fetchSmsCodeApi(@RequestBody @Valid MobileBo bo, HttpServletRequest request) {
        // 根据用户的 ip 进行限制，限制用户在60秒内只能获得一次验证码
        String ip = IPUtil.getIpAddress(request);
        String mobile = bo.getMobile();
        String redisIpKey = RedisKeyUtil.getLoginSmsIpKey(ip);
        String redisCodeKey = RedisKeyUtil.getSmsCodeKey(mobile);

        if (__redis.keyIsExist(redisIpKey)) {
            return Result.error(ResultCode.FREQUENT_OPERATION);
        }

        __redis.setnx60s(redisIpKey, ip);

        // 生成验证码，验证码保存5分钟
        // String randomCode = (int) ((Math.random() * 9 + 1) * 100000) + "";
        // return __sms_utils.sendSMS(mobile, randomCode, redisCodeKey);

        // todo 先将短信验证码写死，后续再修改
        __redis.set(redisCodeKey, "666666", 60 * 5);
        return Result.ok(ResultCode.MOBILE_CODE_SEND);
    }


    @ApiOperation(value = "登陆/注册", notes = "账号存在直接登录，账号不存在自动注册")
    @PostMapping("/login")
    public Result loginApi(@RequestBody @Valid LoginFromBo bo, HttpServletRequest request) {
        String mobile = bo.getMobile();
        String code = bo.getCode();
        String redisKey = RedisKeyUtil.getSmsCodeKey(mobile);
        String smsCode = "666666"; // __redis.get(redisKey);

        // 检验验证码是否匹配
        if (StrUtil.isBlank(smsCode) || !smsCode.equals(code)) {
            return Result.error(ResultCode.CODE_ERROR);
        }

        User user = __user_service.queryUserByMobile(mobile);

        // 用户被冻结
        if (user != null && user.getState() == Constant.USER_DISABLED) {
            return Result.error(ResultCode.ACCOUNT_DISABLE);
        }

        // 注册用户
        Date nowDate = new Date();
        if (user == null) {
            user = new User(
                    IdUtil.getSnowflakeNextIdStr(),
                    mobile, NameUtil.getName(), AvatarUtil.getRandomImg(),
                    Constant.USER_JOB_STATE, Constant.USER_INTRO,
                    bo.getClientId(), 1, nowDate, nowDate
            );
            __user_service.registerUser(user);
        }

        StpUtil.login(user.getId());
        SaSession session = StpUtil.getSession();
        session.set(RedisKeyUtil.getSessionUserKey(), user);
        SaTokenInfo info = StpUtil.getTokenInfo();

        // 记录登录日志
        String ip = IPUtil.getIpAddress(request);
        LoginLog loginLog = new LoginLog(
                IdUtil.getSnowflakeNextIdStr(),
                user.getId(), user.getMobile(), user.getNickname(),
                AddressUtil.getCityInfo(ip), ip,
                nowDate, nowDate, nowDate
        );
        __login_log_service.save(loginLog);

        Map<String, Object> result = new HashMap<>();
        result.put("token", info.getTokenValue());
        result.put("user", user);

        return Result.ok(ResultCode.LOGIN_SUCCESS).data(result);
    }


    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping("/logout")
    public Result logoutApi() {
        StpUtil.logout();
        return Result.ok(ResultCode.LOGOUT_SUCCESS);
    }
}
