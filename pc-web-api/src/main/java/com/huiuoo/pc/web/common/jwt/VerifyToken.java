package com.huiuoo.pc.web.common.jwt;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @param
 * @描述：验证token
 * @创建人：LHF
 * @创建时间：2020/3/18 10:32
 * @return
 */
@Component
public class VerifyToken {

    public void verify(HttpServletRequest request) throws BusinessException {

        String token = request.getHeader("PB-TOKEN");
        if (null == token || token.isEmpty()) {
            throw new BusinessException(EmBusinessError.STATUS_IS_ERROR);
        }
        boolean token_valid = JwtToken.verifyToken(token);
        if (!token_valid) {
            throw new BusinessException(EmBusinessError.STATUS_TOKEN_TIMEOUT);
        }

        long admin_id = JwtToken.getAdminId(token);
        if (admin_id < 0) {
            throw new BusinessException(EmBusinessError.STATUS_TOKEN_INVALID);
        }
        // 存储当前请求用户id
        request.setAttribute(JwtAdminInfo.CURRENT_ADMIN_ID, admin_id);
    }
}
