package org.kuro.recruit.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "LoginFromBo对象", description = "登陆参数")
public class LoginFromBo {

    @ApiModelProperty(value = "手机号")
    @Pattern(regexp = "^(13[0-9]|14[0-9]|15[0-9]|16[2567]|17[0-9]|18[0-9]|19[0-9])\\d{8}$",
            message = "手机号格式错误！")
    @NotBlank(message = "手机号不能为空！")
    private String mobile;

    @Pattern(regexp = "^\\d{6}$", message = "短信验证码格式错误！")
    @ApiModelProperty(value = "短信验证码")
    @NotBlank(message = "短信验证码不能为空！")
    private String code;

    @ApiModelProperty(value = "设备号")
    @NotBlank(message = "设备号不能为空！")
    private String clientId;
}
