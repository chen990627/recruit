package org.kuro.recruit.service;

import org.kuro.recruit.model.entity.LoginLog;

public interface LoginLogService {

    /**
     * 记录登录日志
     *
     * @param log 登录日志
     */
    void save(LoginLog log);
}
