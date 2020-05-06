package com.huiuoo.pc.web.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @描述：
 * @创建人：LHF
 * @创建时间：2019/7/22 13:24
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Resource
    private MyHandlerInterceptor handlerInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry
                .addInterceptor(handlerInterceptor);
        //拦截配置
        addInterceptor.addPathPatterns("/**");
        //排除配置
       /* addInterceptor.excludePathPatterns(
                "/swagger-ui.html/**");*/
    }
}
