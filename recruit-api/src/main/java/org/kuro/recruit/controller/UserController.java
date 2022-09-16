package org.kuro.recruit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kuro.recruit.model.bo.LoginFromBo;
import org.kuro.recruit.model.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/sys")
@Api(value = "用户模块", tags = "用户相关")
public class UserController {

    @ApiOperation(value = "登陆", notes = "登陆")
    @PostMapping("/login")
    public Result loginApi(@RequestBody @Valid LoginFromBo bo) {
        return null;
    }
}
