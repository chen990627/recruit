package org.kuro.recruit.mapper;

import org.apache.ibatis.annotations.Select;
import org.kuro.recruit.model.entity.WorkHistory;
import org.springframework.data.repository.query.Param;
import tk.mybatis.mapper.common.Mapper;

public interface WorkHistoryMapper extends Mapper<WorkHistory> {

    @Select("select id,company,position,start,end,industry,content,user_id from sys_work_history where enabled = 2 and id = #{id}")
    WorkHistory selectNormalById(@Param("id") String id);
}
