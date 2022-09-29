package org.kuro.recruit.config;

public class ApiConfig {

    public static final String BASE_URL = "http://192.168.1.180:8360/api";

    // 登录
    public static final String LOGIN = "/sys/login";

    // 短信验证码
    public static final String SMS = "/sys/sms";

    // 退出登录
    public static final String LOGOUT = "/sys/logout";

    // 添加/修改工作经历，1添加，2修改
    public static final String WORK_SAVE = "/sys/work/save";

    // 删除工作经历，/work/remove/{id}
    public static final String WORK_REMOVE = "/sys/work/remove/";

    // 添加/修改教育经历，1添加，2修改
    public static final String EDU_SAVE = "/sys/edu/save";

    // 根据ID查询工作/教育经历，type:1教育经历，2工作经历，/userInfo/{id}/{type}
    public static final String USERINFO = "/sys/userInfo/";

    // 个人简历信息
    public static final String ACCOUNT = "/sys/account";
}
