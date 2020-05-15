package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.app.common.jwt.JwtToken;
import com.huiuoo.pc.app.common.jwt.JwtUserInfo;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.UserDO;
import com.huiuoo.pc.db.redis.EmRedisKey;
import com.huiuoo.pc.db.redis.RedisUtil;
import com.huiuoo.pc.db.service.IUserService;
import com.huiuoo.pc.db.vo.UserLoginRequest;
import com.huiuoo.pc.db.vo.UserLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * @项目名称：picture-book-new
 * @类描述：用户服务
 * @类创建人：lhf
 * @类创建时间：2020/5/11
 */
@Slf4j
@RequestMapping("user")
@RestController
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * @Description: 获取短信验证码
     * @param:
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("sendSms")
    public void senSms(String phone) throws BusinessException {
        userService.sendSms(phone);
    }

    /**
     * @Description:
     * 1.手机号码登录
     * 2.如果用户不存在直接注册并登录
     * @param:
     * @return: 用户信息和token
     */
    @IgnoreJwtVerify
    @PostMapping("login")
    public UserLoginResponse login(UserLoginRequest request) throws BusinessException {
        UserDO userDO = userService.loginByIdentity(request);
        return new UserLoginResponse(userDO, JwtToken.createToken(userDO.getId()));
    }

    /**
     * @Description: 第三方登录
     * @param:
     * @return: 用户信息和token
     */
    @IgnoreJwtVerify
    @PostMapping("OAuthLogin")
    public UserLoginResponse OAuthLogin(UserLoginRequest request) throws BusinessException {
        UserDO userDO = userService.loginByIdentity(request);
        return new UserLoginResponse(userDO, JwtToken.createToken(userDO.getId()));
    }

    /**
     * @Description: app已登录的用户进入商城时需调用此接口
     * @return: redis中存储的用户信息的key
     */
    @GetMapping("voucher")
    public String voucher() {
        return userService.voucher(JwtUserInfo.getCurrentUserId());
    }
}
