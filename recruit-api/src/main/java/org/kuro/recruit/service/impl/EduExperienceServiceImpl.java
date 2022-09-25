package org.kuro.recruit.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import org.kuro.recruit.exceptions.BusinessException;
import org.kuro.recruit.mapper.EduExperienceMapper;
import org.kuro.recruit.model.entity.EduExperience;
import org.kuro.recruit.model.result.ResultCode;
import org.kuro.recruit.service.EduExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


@Service
public class EduExperienceServiceImpl implements EduExperienceService {

    @Autowired
    private EduExperienceMapper __edu_mapper;


    @Override
    public void saveEduData(EduExperience edu) {
        String loginId = StpUtil.getLoginIdAsString();
        Example example = new Example(EduExperience.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", loginId);
        int count = __edu_mapper.selectCountByExample(example);
        if (count >= 2) {
            throw new BusinessException(ResultCode.EDU_MORE2);
        }

        edu.setId(IdUtil.objectId());
        edu.setUserId(loginId);
        edu.setCreateTime(new Date());
        edu.setUpdateTime(new Date());

        __edu_mapper.insertSelective(edu);
    }


    @Override
    public void updateEduData(EduExperience edu) {
        __edu_mapper.updateByPrimaryKeySelective(edu);
    }


    @Override
    public List<EduExperience> queryEduListByUser(String userId) {
        Example example = new Example(EduExperience.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);

        return __edu_mapper.selectByExample(example);
    }


    @Override
    public EduExperience queryEduById(String id) {
        return __edu_mapper.selectByPrimaryKey(id);
    }

}
