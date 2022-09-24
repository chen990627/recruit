package org.kuro.recruit.model.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "WorkHistBo对象", description = "工作经历参数")
public class WorkHistBo {

    private String id;

    @Range(min = 1, max = 2, message = "1为新增，2为修改")
    private Integer type;

    @NotBlank(message = "公司名称不能为空！")
    private String company;

    @NotBlank(message = "职位名称不能为空！")
    private String position;

    @NotBlank(message = "开始时间不能为空！")
    private String start;

    @NotBlank(message = "结束时间不能为空！")
    private String end;

    private String industry;

    @NotBlank(message = "工作内容不能为空！")
    private String content;
}
