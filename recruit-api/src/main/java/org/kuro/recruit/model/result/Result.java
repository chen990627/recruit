package org.kuro.recruit.model.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    @ApiModelProperty(value = "请求是否成功")
    private Boolean status;

    @ApiModelProperty(value = "返回的状态码")
    private Integer code;

    @ApiModelProperty(value = "返回的消息")
    private String message;

    @ApiModelProperty(value = "返回的数据")
    private Object data;

    /**
     * 构造方法私有化,里面的方法都是静态方法
     * 达到保护属性的作用
     */
    private Result() {}

    /**
     * 这里是使用链式编程
     */
    public static Result ok() {
        Result result = new Result();
        result.setStatus(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setStatus(false);
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return result;
    }

    public static Result error(ResultCode resultCode){
        Result result = new Result();
        result.setStatus(false);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public static Result ok(ResultCode resultCode){
        Result result = new Result();
        result.setStatus(true);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    /**
     * 自定义返回成功与否
     * @param status 请求是否成功
     * @return Result对象
     */
    public Result status(Boolean status){
        this.setStatus(status);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        this.setData(map);
        return this;
    }

    public Result data(Object obj) {
        this.setData(obj);
        return this;
    }
}
