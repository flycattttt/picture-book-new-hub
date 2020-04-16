package com.huiuoo.pc.app.common.jwt;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by mickey on 2018/11/22.
 */
public class JwtUserInfo {
    public static final String CURRENT_USER_ID = "app_current_user_id";

    public static Long getCurrentUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return (Long) request.getAttribute(JwtUserInfo.CURRENT_USER_ID);
    }

}
