package org.kuro.recruit.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houkunlin.system.dict.starter.json.DictText;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_work_history")
public class WorkHistory implements Serializable {

    @Id
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "公司名称")
    private String company;

    @ApiModelProperty(value = "职位名称")
    private String position;

    @ApiModelProperty(value = "开始时间")
    private String start;

    @ApiModelProperty(value = "结束时间")
    private String end;

    @ApiModelProperty(value = "行业")
    private String industry;

    @ApiModelProperty(value = "工作内容")
    private String content;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @DictText("enabled")
    @ApiModelProperty(value = "1删除，2正常")
    private Integer enabled;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateTime;
}
