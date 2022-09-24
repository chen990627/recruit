package org.kuro.recruit.service;


import org.kuro.recruit.model.entity.WorkHistory;

import java.util.List;

public interface WorkHistoryService {

    // 添加工作经历
    void saveWorkHist(WorkHistory workHistory);

    // 修改工作经历
    void updateWorkHist(WorkHistory workHistory);

    // 查询登录用户的工作经历
    List<WorkHistory> queryList(String userId);

    // 根据id查询工作经历
    WorkHistory queryById(String id);

    // 删除工作经历
    void removeWorkHist(String id);
}
