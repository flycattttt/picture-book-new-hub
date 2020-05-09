package com.huiuoo.pc.db.service;


import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.UserDO;
import com.huiuoo.pc.db.vo.UserLoginRequest;

import java.security.NoSuchAlgorithmException;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
public interface IUserService {

    /** 发送短信验证码 */
    void sendSms(String phone) throws BusinessException;

    /** 用户登录或注册 */
    UserDO loginByIdentity(UserLoginRequest request) throws BusinessException;
}
