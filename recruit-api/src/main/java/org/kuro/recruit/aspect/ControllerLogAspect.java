package org.kuro.recruit.aspect;

import cn.hutool.core.date.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.kuro.recruit.utils.AddressUtil;
import org.kuro.recruit.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ControllerLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(* org.kuro.recruit.controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 获取用户IP地址
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String ip = IPUtil.getIpAddress(request);
        // 访问地点
        String location = AddressUtil.getCityInfo(ip);
        // 访问时间
        String now = DateUtil.date().toString();
        // 访问方法
        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        logger.warn(String.format("用户[%s]，于[%s]，在[%s]，访问了[%s].", ip, now, location, target));
    }
}
