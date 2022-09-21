package org.kuro.recruit.service.impl;

import org.kuro.recruit.mapper.LoginLogMapper;
import org.kuro.recruit.model.entity.LoginLog;
import org.kuro.recruit.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper __log_mapper;


    @Override
    public void save(LoginLog log) {
        __log_mapper.insertSelective(log);
    }
}
