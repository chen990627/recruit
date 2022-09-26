package org.kuro.recruit.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kuro.recruit.config.Constant;
import org.kuro.recruit.model.bo.EduBo;
import org.kuro.recruit.model.bo.LoginFromBo;
import org.kuro.recruit.model.bo.MobileBo;
import org.kuro.recruit.model.bo.WorkHistBo;
import org.kuro.recruit.model.entity.EduExperience;
import org.kuro.recruit.model.entity.LoginLog;
import org.kuro.recruit.model.entity.User;
import org.kuro.recruit.model.entity.WorkHistory;
import org.kuro.recruit.model.result.Result;
import org.kuro.recruit.model.result.ResultCode;
import org.kuro.recruit.service.EduExperienceService;
import org.kuro.recruit.service.LoginLogService;
import org.kuro.recruit.service.UserService;
import org.kuro.recruit.service.WorkHistoryService;
import org.kuro.recruit.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private WorkHistoryService __work_history_service;

    @Autowired
    private EduExperienceService __edu_experience_service;


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
                    IdUtil.getSnowflakeNextIdStr(), mobile, 1,
                    DesensitizedUtil.mobilePhone(mobile), AvatarUtil.getRandomImg(),
                    Constant.USER_JOB_STATE, Constant.USER_INTRO,
                    bo.getClientId(), 1, null, nowDate, nowDate
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
                user.getId(), user.getMobile(), user.getName(),
                AddressUtil.getCityInfo(ip), ip,
                nowDate, nowDate, nowDate
        );
        __login_log_service.save(loginLog);

        Map<String, Object> result = new HashMap<>();
        result.put("token", info.getTokenValue());
        result.put("user", user);

        // todo 登录第三方IM，如腾讯IM...

        return Result.ok(ResultCode.LOGIN_SUCCESS).data(result);
    }


    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping("/logout")
    public Result logoutApi() {
        StpUtil.logout();
        return Result.ok(ResultCode.LOGOUT_SUCCESS);
    }


    @ApiOperation(value = "添加/修改工作经历", notes = "添加/修改工作经历，1添加，2修改")
    @PostMapping("/work/save")
    public Result saveWorkHistoryApi(@RequestBody @Valid WorkHistBo bo) {
        WorkHistory history = new WorkHistory();
        BeanUtils.copyProperties(bo, history);
        if (bo.getType() == 1) {
            __work_history_service.saveWorkHist(history);
            return Result.ok(ResultCode.ADD_SUCCESS);
        } else {
            __work_history_service.updateWorkHist(history);
            return Result.ok(ResultCode.UPDATE_SUCCESS);
        }
    }


    @ApiOperation(value = "删除工作经历", notes = "删除工作经历")
    @PostMapping("/work/remove/{id}")
    public Result removeWorkHistoryApi(@PathVariable("id") String id) {
        __work_history_service.removeWorkHist(id);
        return Result.ok(ResultCode.DELETE_SUCCESS);
    }


    @ApiOperation(value = "添加/修改教育经历", notes = "添加/修改教育经历，1添加，2修改")
    @PostMapping("/edu/save")
    public Result saveEduApi(@RequestBody @Valid EduBo bo) {
        EduExperience experience = new EduExperience();
        BeanUtils.copyProperties(bo, experience);
        if (bo.getType() == 1) {
            __edu_experience_service.saveEduData(experience);
            return Result.ok(ResultCode.ADD_SUCCESS);
        } else {
            __edu_experience_service.updateEduData(experience);
            return Result.ok(ResultCode.UPDATE_SUCCESS);
        }
    }


    @ApiOperation(value = "查询工作/教育经历", notes = "根据ID查询工作/教育经历，type:1教育经历，2工作经历")
    @PostMapping("/userInfo/{id}/{type}")
    public Result fetchEduApi(@PathVariable("id") String id, @PathVariable("type") Integer type) {
        switch (type) {
            case 1:
                EduExperience edu = __edu_experience_service.queryEduById(id);
                return Result.ok().data(edu);
            case 2:
                WorkHistory history = __work_history_service.queryById(id);
                return Result.ok().data(history);
//            case 3:
//                JobExpect expect = jobExpectService.queryById(id);
//                return Result.ok().data(expect);
            default:
                return Result.error(ResultCode.PARAM_WRONGFUL);
        }
    }
}
