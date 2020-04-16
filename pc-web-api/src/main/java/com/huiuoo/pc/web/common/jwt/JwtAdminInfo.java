package com.huiuoo.pc.web.common.jwt;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by mickey on 2018/11/22.
 */
public class JwtAdminInfo {
    public static final String CURRENT_ADMIN_ID = "app_current_admin_id";

    public static Long getCurrentAdminId() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return (Long) request.getAttribute(JwtAdminInfo.CURRENT_ADMIN_ID);
    }

}
