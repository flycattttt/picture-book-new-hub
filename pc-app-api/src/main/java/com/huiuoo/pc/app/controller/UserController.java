package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.app.common.jwt.JwtToken;
import com.huiuoo.pc.app.common.jwt.JwtUserInfo;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.redis.EmRedisKey;
import com.huiuoo.pc.common.redis.RedisUtil;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.db.dataobject.UserDO;
import com.huiuoo.pc.db.service.IUserService;
import com.huiuoo.pc.db.vo.UserRequest;
import com.huiuoo.pc.db.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

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

    private final RedisUtil redisUtil;

    public UserController(IUserService userService, RedisUtil redisUtil) {
        this.userService = userService;
        this.redisUtil = redisUtil;
    }

    @IgnoreJwtVerify
    @GetMapping("sendSms")
    public Object senSms(String phone) throws BusinessException {

        if (StringUtils.isBlank(phone)){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR,"手机号码不能为空");
        }

        System.out.println("phone-> " +phone);
        // 手机号码验证
        CommonUtils.validatorPhone(phone);

        Object temporary = redisUtil.hget(EmRedisKey.USER_MESSAGE_CODE.getKey() + "_temporary", phone);
        if (temporary != null) {
            throw new BusinessException(EmBusinessError.SEND_MESSAGE_CODE_FORBID);
        }

        String smsCode = userService.sendSms(phone);
        // 同一手机号码60秒内不能重复发送
        redisUtil.hset(EmRedisKey.USER_MESSAGE_CODE.getKey() + "_temporary", phone, 1, 60);
        // 验证码有效期30分钟
        redisUtil.hset(EmRedisKey.USER_MESSAGE_CODE.getKey(), phone, smsCode, 1800);
        return null;
    }

    @IgnoreJwtVerify
    @PostMapping("login")
    public UserResponse login(UserRequest request) throws BusinessException, NoSuchAlgorithmException {

        // 手机号码登录
        if (!request.phoneValidate()) {
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        // 检验手机号码格式
        CommonUtils.validatorPhone(request.getPhone());

        //校验短信验证码
        String code = (String) redisUtil.hget(EmRedisKey.USER_MESSAGE_CODE.getKey(), request.getPhone());
        if (!StringUtils.equals(code, request.getSmsCode())) {
            throw new BusinessException(EmBusinessError.SMSCODE_IS_ERROR);
        }

        UserDO userDO = userService.loginByPhone(request);
        return covert2UserResponse(userDO, JwtToken.createToken(userDO.getId()));
    }

    @IgnoreJwtVerify
    @PostMapping("OAuthLogin")
    public UserResponse OAuthLogin(UserRequest request) throws BusinessException {
        if (!request.oauthValidate()){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        UserDO userDO = userService.loginByOAuth(request);
        return covert2UserResponse(userDO, JwtToken.createToken(userDO.getId()));
    }

    private UserResponse covert2UserResponse(UserDO userDO, String token) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(userDO, response);
        response.setToken(token);
        return response;
    }

    /**
     * @Description: 查看用户id
     * @param:[]
     * @return: 用户id
     */
    @PostMapping("getUserId")
    public Long getUserInfo() {
        return JwtUserInfo.getCurrentUserId();
    }

    @IgnoreJwtVerify
    @GetMapping("test")
    public String test() {
        return "sadas";
    }

    @IgnoreJwtVerify
    @GetMapping("test2")
    public void tesasdt() {
        
    }
}
