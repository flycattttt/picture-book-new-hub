package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.app.common.jwt.JwtToken;
import com.huiuoo.pc.app.common.jwt.JwtUserInfo;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.UserDO;
import com.huiuoo.pc.db.service.IUserService;
import com.huiuoo.pc.db.vo.UserLoginRequest;
import com.huiuoo.pc.db.vo.UserLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@Slf4j
@RequestMapping("user")
@RestController
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @IgnoreJwtVerify
    @GetMapping("sendSms")
    public void senSms(String phone) throws BusinessException {
        userService.sendSms(phone);
    }

    @IgnoreJwtVerify
    @PostMapping("login")
    public UserLoginResponse login(UserLoginRequest request) throws BusinessException {
        System.out.println("-----login-----"+request.toString());
        UserDO userDO = userService.loginByIdentity(request);
        return new UserLoginResponse(userDO, JwtToken.createToken(userDO.getId()));
    }

    @IgnoreJwtVerify
    @PostMapping("OAuthLogin")
    public UserLoginResponse OAuthLogin(UserLoginRequest request) throws BusinessException {
        System.out.println("----oauth----"+request.toString());
        UserDO userDO = userService.loginByIdentity(request);
        return new UserLoginResponse(userDO, JwtToken.createToken(userDO.getId()));
    }

    /**
     * @Description: 查看用户id
     * @return: 用户id
     */
    @PostMapping("getUserId")
    public Long getUserId() {
        return JwtUserInfo.getCurrentUserId();
    }
}
