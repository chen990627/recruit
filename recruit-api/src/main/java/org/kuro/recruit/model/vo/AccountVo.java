package org.kuro.recruit.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kuro.recruit.model.entity.EduExperience;
import org.kuro.recruit.model.entity.User;
import org.kuro.recruit.model.entity.WorkHistory;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountVo extends User {

    List<EduExperience> eduList;

    List<WorkHistory> workList;
}
