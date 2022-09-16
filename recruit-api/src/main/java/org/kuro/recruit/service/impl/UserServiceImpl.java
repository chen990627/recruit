package org.kuro.recruit.service.impl;

import org.kuro.recruit.mapper.UserMapper;
import org.kuro.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
}
