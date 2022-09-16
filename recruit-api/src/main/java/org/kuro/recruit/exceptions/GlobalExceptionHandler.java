package org.kuro.recruit.exceptions;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.extern.slf4j.Slf4j;
import org.kuro.recruit.model.result.Result;
import org.kuro.recruit.model.result.ResultCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.UnexpectedTypeException;
import java.sql.SQLException;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 参数异常
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result error(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return Result.error().message(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }


    /**
     * body 请求体为空
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result error(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return Result.error(ResultCode.REQUEST_BODY_IS_BLANK);
    }


    /**
     * query 请求参数缺失
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result error(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        return Result.error(ResultCode.PARAM_NOT_COMPLETE);
    }


    @ExceptionHandler(MysqlDataTruncation.class)
    public Result error(MysqlDataTruncation e) {
        log.error(e.getMessage());
        return Result.error(ResultCode.DATABASE_TOO_LONG_EXCEPTION);
    }


    /**
     * 数据库相关异常
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(SQLException.class)
    public Result error(SQLException e) {
        log.error(e.getMessage());
        return Result.error(ResultCode.DATABASE_EXCEPTION);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public Result error(ArrayIndexOutOfBoundsException e) {
        log.error(e.getMessage());
        return Result.error().message("数组索引越界异常");
    }


    @ExceptionHandler(NumberFormatException.class)
    public Result error(NumberFormatException e) {
        log.error(e.getMessage());
        return Result.error().message("数字格式化异常");
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result error(HttpMediaTypeNotSupportedException e) {
        log.error(e.getMessage());
        return Result.error().message("参数类型必须为json！");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result error(MaxUploadSizeExceededException e) {
        log.error(e.getMessage());
        return Result.error().message("文件大小超过限制！");
    }


    /**
     * 请求类型错误
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result error(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage());
        return Result.error().message("HTTP请求类型错误");
    }


    /**
     * 注解与检验类型不匹配
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public Result error(UnexpectedTypeException e) {
        log.error(e.getMessage());
        return Result.error().message("注解与检验类型不匹配（后端错误，与前端无关）");
    }


    /**
     * 权限相关错误
     *
     * @param e 错误对象
     * @return Result
     */
    @ExceptionHandler(NotLoginException.class)
    public Result error(NotLoginException e) {
        log.error(e.getMessage());
        switch (e.getType()) {
            case NotLoginException.INVALID_TOKEN:
            case NotLoginException.TOKEN_TIMEOUT:
                // token已过期
                // token无效
                return Result.error(ResultCode.CREDENTIALS_EXPIRED);
            case NotLoginException.BE_REPLACED:
                // token已被顶下线
                return Result.error(ResultCode.ACCOUNT_USE_BY_OTHERS);
            case NotLoginException.KICK_OUT:
                // token已被踢下线
                return Result.error(ResultCode.ACCOUNT_FORCED_OFFLINE);
            case NotLoginException.NOT_TOKEN:
                // 未提供token
            default:
                // 当前会话未登录
                return Result.error(ResultCode.USER_NOT_LOGIN);
        }
    }


    /**
     * 没有角色
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(NotRoleException.class)
    public Result error(NotRoleException e) {
        log.error(e.getMessage());
        return Result.error(ResultCode.NO_PERMISSION);
    }


    /**
     * 没有权限
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result error(NotPermissionException e) {
        log.error(e.getMessage());
        return Result.error(ResultCode.NO_PERMISSION);
    }


    /**
     * 处理业务异常,我们自己定义的异常
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(BusinessException.class)
    public Result error(BusinessException e) {
        e.printStackTrace();
        log.error(e.getErrMsg());
        return Result.error().code(e.getCode()).message(e.getErrMsg());
    }

    /**
     * 全局异常处理,没有指定异常的类型,不管什么异常都是可以捕获的
     *
     * @param e 错误
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(ResultCode.SYSTEM_ERROR);
    }
}
