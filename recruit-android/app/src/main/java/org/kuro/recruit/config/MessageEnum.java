package org.kuro.recruit.config;

public enum MessageEnum {

    FUNCTION_NOT_EXIST("暂未开放此功能!"),
    MOBILE_PATTERN_ERROR("手机号格式不合法!"),
    MOBILE_NOT_EMPTY("手机号不能为空!"),
    SMS_PATTERN_ERROR("验证码格式不合法!"),
    SMS_NOT_EMPTY("验证码不能为空!"),
    AGREEMENT_NOT("未同意《用户协议》!");

    private final String message;

    MessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
