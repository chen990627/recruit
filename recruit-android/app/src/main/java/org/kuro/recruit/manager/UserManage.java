package org.kuro.recruit.manager;

import org.kuro.recruit.model.entity.User;
import org.kuro.recruit.utils.SpUtil;

public class UserManage {

    public static User account() {
        String nickname = SpUtil.getInstance().getString("nickname", "");
        String avatar = SpUtil.getInstance().getString("avatar", "");
        String describe = SpUtil.getInstance().getString("describe", "");
        String mobile = SpUtil.getInstance().getString("mobile", "");

        return new User(mobile, nickname, avatar, describe);
    }


    public static String getToken() {
        return SpUtil.getInstance().getString("token", "");
    }


    public static void login(User user, String token) {
        SpUtil.getInstance().putString("token", token);
        SpUtil.getInstance().putString("userId", user.getId());
        SpUtil.getInstance().putString("nickname", user.getNickname());
        SpUtil.getInstance().putString("avatar", user.getAvatar());
        SpUtil.getInstance().putString("mobile", user.getMobile());
        SpUtil.getInstance().putString("describe", user.getSelfDescribe());
    }


    public static void logout() {
        SpUtil.getInstance().deleteKey("token");
        SpUtil.getInstance().deleteKey("userId");
        SpUtil.getInstance().deleteKey("nickname");
        SpUtil.getInstance().deleteKey("avatar");
        SpUtil.getInstance().deleteKey("mobile");
        SpUtil.getInstance().deleteKey("describe");
    }
}
