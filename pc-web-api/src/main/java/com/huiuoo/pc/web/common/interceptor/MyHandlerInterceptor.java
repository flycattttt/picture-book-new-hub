package com.huiuoo.pc.web.common.interceptor;


import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.web.common.jwt.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @描述：
 * @创建人：LHF
 * @创建时间：2019/7/22 13:24
 */
@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    private final VerifyToken verifyToken;

    public MyHandlerInterceptor(VerifyToken verifyToken) {
        this.verifyToken = verifyToken;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BusinessException {

        //判断请求路径是否是接口地址
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            //判断该方法是否被@IgnoreJwtVerify修饰
            if (AnnotatedElementUtils.isAnnotated(method, IgnoreJwtVerify.class)) {
                return true;
            }
            //验证token
            verifyToken.verify(request);
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
