package org.kuro.recruit.model.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "EduBo对象", description = "教育经历参数")
public class EduBo {

    private String id;

    @NotBlank(message = "学历不能为空！")
    private String edu;

    @NotBlank(message = "学校不能为空！")
    private String college;

    @NotBlank(message = "就读时间段不能为空！")
    private String timeQuantum;

    @NotBlank(message = "专业不能为空！")
    private String major;

    @NotNull(message = "1为新增，2为修改")
    @Range(min = 1, max = 2, message = "1为新增，2为修改")
    private Integer type;
}
