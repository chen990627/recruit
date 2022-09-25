package org.kuro.recruit.service;


import org.kuro.recruit.model.entity.EduExperience;

import java.util.List;


public interface EduExperienceService {

    // 保存教育经历
    void saveEduData(EduExperience edu);

    // 修改教育经历
    void updateEduData(EduExperience edu);

    // 查询登录用户的教育经历列表
    List<EduExperience> queryEduListByUser(String userId);

    // 根据id查询教育经历
    EduExperience queryEduById(String id);
}
