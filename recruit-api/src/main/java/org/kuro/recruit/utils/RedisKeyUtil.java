package org.kuro.recruit.utils;


public class RedisKeyUtil {

    private static final String SPLIT = ":";

    private static final String PREFIX_LOGIN_USER = "loginUser";

    private static final String PREFIX_MOBILE = "mobile";

    private static final String PREFIX_CODE = "smsCode";

    private static final String PREFIX_LOGIN_SMS = "loginSms";

    private static final String PREFIX_UV = "uv";

    private static final String PREFIX_DAU = "dau";


    /**
     * 返回某个ip获取过登录验证码的记录
     *
     * @param ip ip地址
     * @return 存入redis的key
     */
    public static String getLoginSmsIpKey(String ip) {
        return PREFIX_MOBILE + SPLIT + PREFIX_CODE + SPLIT + PREFIX_LOGIN_SMS + SPLIT + ip;
    }


    /**
     * 短信验证码
     *
     * @param mobile 手机号
     * @return 存入redis的key
     */
    public static String getSmsCodeKey(String mobile) {
        return PREFIX_MOBILE + SPLIT + PREFIX_CODE + SPLIT + PREFIX_LOGIN_SMS + SPLIT + mobile;
    }


    /**
     * 单日UV
     *
     * @param date 时间
     * @return 存入redis的key
     */
    public static String getUVKey(String date) {
        return PREFIX_UV + SPLIT + date;
    }


    /**
     * 区间UV
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 存入redis的key
     */
    public static String getUVKey(String startDate, String endDate) {
        return PREFIX_UV + SPLIT + startDate + SPLIT + endDate;
    }


    /**
     * 单日活跃用户
     *
     * @param date 时间
     * @return 存入redis的key
     */
    public static String getDAUKey(String date) {
        return PREFIX_DAU + SPLIT + date;
    }


    /**
     * 区间活跃用户
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 存入redis的key
     */
    public static String getDAUKey(String startDate, String endDate) {
        return PREFIX_DAU + SPLIT + startDate + SPLIT + endDate;
    }


    /**
     * session中当前登录用户的key
     *
     * @return sessionKey
     */
    public static String getSessionUserKey() {
        return PREFIX_LOGIN_USER;
    }
}
