package org.kuro.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.kuro.recruit.model.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    User selectUserByMobile(@Param("mobile") String mobile);
}
