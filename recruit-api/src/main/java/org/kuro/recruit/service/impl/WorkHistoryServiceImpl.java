package org.kuro.recruit.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import org.kuro.recruit.exceptions.BusinessException;
import org.kuro.recruit.mapper.WorkHistoryMapper;
import org.kuro.recruit.model.entity.WorkHistory;
import org.kuro.recruit.model.result.ResultCode;
import org.kuro.recruit.service.WorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class WorkHistoryServiceImpl implements WorkHistoryService {

    @Autowired
    private WorkHistoryMapper __work_mapper;


    @Override
    public void saveWorkHist(WorkHistory workHistory) {
        String loginId = StpUtil.getLoginIdAsString();
        Example example = new Example(WorkHistory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", loginId);
        criteria.andEqualTo("enabled", 2);
        int count = __work_mapper.selectCountByExample(example);
        if (count >= 4) {
            throw new BusinessException(ResultCode.WORK_HIST_MORE4);
        }

        workHistory.setId(IdUtil.objectId());
        workHistory.setUserId(loginId);
        workHistory.setEnabled(2);
        workHistory.setCreateTime(new Date());
        workHistory.setUpdateTime(new Date());

        __work_mapper.insertSelective(workHistory);
    }


    @Override
    public void updateWorkHist(WorkHistory workHistory) {
        __work_mapper.updateByPrimaryKeySelective(workHistory);
    }


    @Override
    public List<WorkHistory> queryList(String userId) {
        Example example = new Example(WorkHistory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("enabled", 2);
        return __work_mapper.selectByExample(example);
    }


    @Override
    public WorkHistory queryById(String id) {
        return __work_mapper.selectNormalById(id);
    }


    @Override
    public void removeWorkHist(String id) {
        WorkHistory history = queryById(id);
        if (history == null || !Objects.equals(history.getUserId(), StpUtil.getLoginIdAsString())) {
            throw new BusinessException(ResultCode.WORK_NOT_EXIT);
        }
        history.setEnabled(1);

        __work_mapper.updateByPrimaryKeySelective(history);
    }
}
