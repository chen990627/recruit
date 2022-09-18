package org.kuro.recruit.utils;

import java.util.Random;

public class AvatarUtil {

    private static final String[] avatar = {
            "https://nlgc-api.oss-cn-shenzhen.aliyuncs.com/avatar/1.png",
            "https://nlgc-api.oss-cn-shenzhen.aliyuncs.com/avatar/2.png",
            "https://nlgc-api.oss-cn-shenzhen.aliyuncs.com/avatar/3.png",
            "https://nlgc-api.oss-cn-shenzhen.aliyuncs.com/avatar/4.png"
    };

    public static String getRandomImg() {
        int size = avatar.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return avatar[index];
    }
}
