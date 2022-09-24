package org.kuro.recruit.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houkunlin.system.dict.starter.json.DictText;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user")
public class User implements Serializable {

    @Id
    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @DictText("gender")
    @ApiModelProperty(value = "1男，2女")
    private Integer gender;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    // 离职-随时到岗
    @ApiModelProperty(value = "求职状态")
    private String jobState;

    @ApiModelProperty(value = "自我描述")
    private String selfDescribe;

    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    @DictText("user_state")
    @ApiModelProperty(value = "1正常，2冻结")
    private Integer state;

    @ApiModelProperty(value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date birthday;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateTime;
}
