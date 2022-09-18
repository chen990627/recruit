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
    DATA_LIST_MANY(1001, "数据最多创建3条!"),
    CREATE_ONE_COLLECTION(1000, "不能重复收藏"),
    NOT_DATA(1000, "不存在数据"),
    RESUME_NOT(1000, "请填写基本信息"),

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
    FREQUENT_OPERATION(500, "操作频繁，请60秒后重试！"),
    ACCOUNT_DISABLE(500, "账号被冻结，请联系管理员！"),
    ACCOUNT_IS_EXIST(500, "该手机号已被注册！"),
    WORK_HIST_MORE4(500, "工作经历个数最多为4个！"),
    EDU_MORE2(500, "教育经历个数最多为2个！"),
    RESUME_MORE3(500, "简历附件最多上传3个！"),
    WORK_NOT_EXIT(500, "该经历不存在或您无法删除别人的工作经历！"),
    GENDER_ERROR(500, "性别请传1(男)或2(女)！"),
    IDENTITY_ERROR(500, "职业类型不符！"),
    JOB_EXPECT_MORE3(500, "求职意向最多添加3个！"),
    PASSWORD_NOT_ALIKE(500, "两次密码不一致！"),
    PASSWORD_IS_BLANK(500, "密码不能为空！"),
    INVITATION_CODE_IS_BLANK(500, "邀请码不能为空！"),
    INVITATION_CODE_ERROR(500, "邀请码过期或错误！"),
    USER_TYPE_ERROR(500, "您不是企业用户，无法获取企业信息！"),
    ACCOUNT_NOT_AUTH(500, "您尚未进行实名认证，请先前往认证！"),
    COMPANY_NOT_AUTH(500, "您尚未进行企业认证，请先前往认证后重试！"),
    COMPANY_WAITING_FOR_AUDIT(500, "您已提交企业信息，请等待审核通过！"),
    ID_CARD_ERROR(500, "身份证格式不合法！"),
    IDENTIFY_COMMIT(500, "实名认证已提交，请等待审核通过！"),
    CODE_SEND_ERROR(500, "短信发送失败，请稍后重试！"),
    CODE_ERROR(500, "验证码过期或不匹配，请稍后重试！"),
    CODE_TYPE_ERROR(500, "验证码类型错误！"),
    LOGIN_TYPE_ERROR(500, "登录类型错误！"),
    OPEN_ID_FAIL(500, "openid解析失败！"),
    WECHAT_MOBILE_FAIL(500, "微信手机号解析失败！"),

    NETTY_START_FAIL(500, "Netty启动失败！"),
    MESSAGE_IS_BLANK(500, "消息体不能为空！"),
    MESSAGE_SEND_ERROR(500, "消息保存失败！"),

    TODAY_IS_SIGN_IN(500, "您今天已签到！"),

    ORDER_NOT_VALID(500, "您没有这个订单！"),
    ORDER_IS_PAY(500, "该订单已付款！"),
    WECHAT_PAY_ERROR(500, "微信支付失败！"),
    ALI_PAY_ERROR(500, "支付宝支付失败！"),
    GOODS_NOT_EXIST(500, "商品不存在！"),
    WITHDRAW_CASH_PARAM_BUILD_ERROR(500, "提现参数构建失败！"),
    WITHDRAW_CASH_ERROR(500, "提现失败！"),

    UPLOAD_FILE_ERROR(500, "上传失败！"),
    DELETE_FILE_ERROR(500, "无法删除该图片！"),
    UPLOAD_MORE_THAN_10_TIMES(500, "今天上传图片已超过10次，请明天再试！"),

    DATE_ERROR(999, "开始时间不能大于结束时间"),

    APPEAL_NOT_AUTH(999, "无法查看他人信息"),
    COMPANY_NOT_ERROR(999, "企业信息不存在,请前往查看认证审核是否通过"),

    INDUSTRY_ERROR(999, "行业类型错误"),
    RESUME_ERROR(999, "获取求职意向失败"),
    INTENTION_ERROR(999, "没有符合条件的招聘信息"),

    COLLECTION_ERROR(999, "收藏失败"),
    MONEY_ERROR(999, "余额不足"),
    BEFORE_MONEY_ERROR(999, "预付薪资与薪资不相等"),
    REWARD_ERROR(999, "悬赏类型错误"),

    ID_CARD_IDENTIFY_ERROR(500, "实名认证失败，请稍后重试！"),
    USER_IS_IDENTIFIED(500, "您已实名，不可重复认证！"),
    USER_NOT_IDENTIFIED(500, "您尚未进行实名认证！"),

    CREDIT_CODE_ERROR(500, "统一社会信用码格式不正确！"),

    API_V3_KEY_ERROR(500, "无效的ApiV3Key，长度必须为32个字节!"),
    NOT_SUPPORT_RSA(500, "当前Java环境不支持RSA!"),
    PRI_KEY_ERROR(500, "无效的密钥格式!"),
    FETCH_SING_ERROR(500, "获取签名失败!"),

    GET_REQUEST_ERROR(500, "get请求失败!"),

    HAS_ASSOCIATED_DATA(900, "该数据有关联数据，操作失败！"),
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
