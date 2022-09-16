package org.kuro.recruit.model.result;

public enum ResultCode implements CustomizeResultCode {
    /* 成功 */
    SUCCESS(200, "请求成功！"),
    LOGIN_SUCCESS(200, "登录成功！"),
    LOGOUT_SUCCESS(200, "已注销登录！"),
    REGISTER_SUCCESS(200, "注册成功！"),
    SIGN_IN_SUCCESS(200, "签到成功！"),
    MOBILE_CODE_SEND(200, "短信已发送，请注意查收！"),
    DATA_DOES_NOT_EXIST(200, "暂无数据！"),

    /* 默认失败 */
    COMMON_FAIL(999, "请求失败！"),

    PARAM_WRONGFUL(1000, "参数不合法！"),
    PARAM_TYPE_ERROR(1000, "参数类型错误！"),
    PARAM_NOT_COMPLETE(1000, "参数缺失！"),
    REQUEST_BODY_IS_BLANK(1000, "参数类型错误或参数缺失！"),

    ADD_SUCCESS(3800, "添加成功！"),
    ADD_ERROR(3800, "添加失败！"),
    UPDATE_SUCCESS(3800, "修改成功！"),
    UPDATE_ERROR(3800, "修改失败！"),
    DELETE_SUCCESS(3800, "删除成功！"),
    DELETE_ERROR(3800, "删除失败！"),

    USER_NOT_LOGIN(520, "尚未登录，请登录！"),
    NO_PERMISSION(500, "权限不足！"),
    CREDENTIALS_EXPIRED(577, "令牌错误或过期，请重新登录！"),
    ACCOUNT_USE_BY_OTHERS(577, "您的账号已在另一台设备登录！"),
    ACCOUNT_FORCED_OFFLINE(577, "您已被强制下线！"),
    ACCOUNT_DISABLE(500, "账号被冻结，请联系管理员！"),
    ACCOUNT_NOT_EXIST(500, "用户不存在！"),
    ACCOUNT_INFO_ERROR(500, "账号或密码错误！"),
    ACCOUNT_IS_EXIST(500, "该手机号已被注册！"),
    GENDER_ERROR(500, "性别请传1(男)或2(女)！"),
    FREQUENT_OPERATION(500, "操作频繁，请60秒后重试！"),
    PASSWORD_NOT_ALIKE(500, "两次密码不一致！"),
    PASSWORD_IS_BLANK(500, "密码不能为空！"),
    INVITATION_CODE_IS_BLANK(500, "邀请码不能为空！"),
    INVITATION_CODE_ERROR(500, "邀请码过期或错误！"),
    ID_CARD_ERROR(500, "身份证格式不合法！"),
    IDENTIFY_COMMIT(500, "实名认证已提交，请等待审核通过！"),
    SMS_CODE_SEND_ERROR(500, "短信发送失败，请稍后重试！"),
    CODE_CREATE_ERROR(500, "验证码生成失败！"),
    CODE_ERROR(500, "验证码过期或不匹配！"),
    LOGIN_TYPE_ERROR(500, "登录类型错误！"),
    OPEN_ID_FAIL(500, "openid解析失败！"),
    WECHAT_MOBILE_FAIL(500, "微信手机号解析失败！"),

    WECHAT_PAY_ERROR(500, "微信支付失败！"),
    ALI_PAY_ERROR(500, "支付宝支付失败！"),
    WITHDRAW_CASH_ERROR(500, "提现失败！"),

    UPLOAD_FILE_ERROR(500, "上传失败！"),
    DELETE_FILE_ERROR(500, "无法删除该图片！"),

    DATE_ERROR(999, "开始时间不能大于结束时间"),

    MONEY_ERROR(999,"余额不足"),

    ID_CARD_IDENTIFY_ERROR(500, "实名认证失败，请稍后重试！"),
    USER_IS_IDENTIFIED(500, "您已实名，不可重复认证！"),
    USER_NOT_IDENTIFIED(500, "您尚未进行实名认证！"),

    CREDIT_CODE_ERROR(500, "统一社会信用码格式不正确！"),

    API_V3_KEY_ERROR(500, "无效的ApiV3Key，长度必须为32个字节!"),
    NOT_SUPPORT_RSA(500, "当前Java环境不支持RSA!"),
    PRI_KEY_ERROR(500, "无效的密钥格式!"),
    FETCH_SING_ERROR(500, "获取签名失败!"),

    DATABASE_TOO_LONG_EXCEPTION(900, "数据库表字段长度不符！"),
    DATABASE_EXCEPTION(900, "数据库异常！"),

    SYSTEM_ERROR(9999, "系统繁忙，请稍后重试！");

    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
