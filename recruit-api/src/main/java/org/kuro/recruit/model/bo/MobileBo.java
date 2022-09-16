package org.kuro.recruit.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "MobileBo对象", description = "短信验证码参数")
public class MobileBo {

    @ApiModelProperty(value = "手机号")
    @Pattern(regexp = "^(13[0-9]|14[0-9]|15[0-9]|16[2567]|17[0-9]|18[0-9]|19[0-9])\\d{8}$",
            message = "手机号格式错误！")
    @NotBlank(message = "手机号不能为空！")
    private String mobile;
}
