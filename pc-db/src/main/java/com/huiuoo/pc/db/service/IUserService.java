package com.huiuoo.pc.db.service;


import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.UserDO;
import com.huiuoo.pc.db.vo.UserRequest;

import java.security.NoSuchAlgorithmException;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
public interface IUserService {

    /** 发送 6 位数验证码 */
    String sendSms(String phone) throws BusinessException;

    /** 用户使用手机号登录或注册 */
    UserDO loginByPhone(UserRequest request) throws NoSuchAlgorithmException;
    /** 用户使用第三方账号登录或注册 */
    UserDO loginByOAuth(UserRequest request);
}
