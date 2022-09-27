package org.kuro.recruit.service;

import org.kuro.recruit.model.entity.User;

public interface UserService {

    /**
     * 根据手机号查询用户
     *
     * @param mobile 手机号
     * @return 用户对象
     */
    User queryUserByMobile(String mobile);


    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    void registerUser(User user);


    /**
     * 根据ID查询用户
     *
     * @param id ID
     */
    User queryById(String id);
}
