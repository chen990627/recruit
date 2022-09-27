package org.kuro.recruit.service.impl;

import org.kuro.recruit.mapper.UserMapper;
import org.kuro.recruit.model.entity.User;
import org.kuro.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper __user_mapper;


    @Override
    public User queryUserByMobile(String mobile) {
        return __user_mapper.selectUserByMobile(mobile);
    }


    @Override
    public void registerUser(User user) {
        __user_mapper.insertSelective(user);
    }


    @Override
    public User queryById(String id) {
        return __user_mapper.selectByPrimaryKey(id);
    }
}
