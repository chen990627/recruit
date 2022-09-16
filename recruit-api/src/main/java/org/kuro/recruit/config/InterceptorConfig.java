package org.kuro.recruit.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${ignored}")
    private List<String> ignored;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new SaRouteInterceptor(((request, response, handler) -> {
            // 拦截所有URL请求
            SaRouter.match("/**", StpUtil::checkLogin);
        }))).addPathPatterns("/**").excludePathPatterns(ignored);

    }
}
