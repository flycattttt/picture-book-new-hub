package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.common.utils.JuheApi;
import com.huiuoo.pc.db.dao.UserDao;
import com.huiuoo.pc.db.dataobject.UserDO;
import com.huiuoo.pc.db.service.IUserService;
import com.huiuoo.pc.db.vo.UserRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@Service
public class UserServiceImpl implements IUserService {

   // @Autowired
    //private UserDao userDao;

    @Override
    public String sendSms(String phone) throws BusinessException {
        if (StringUtils.isBlank(phone)) {
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR, "手机号码不能为空");
        }
        String smsCode = CommonUtils.getSmsCode();
        JuheApi.sendValidationCode(phone, smsCode);
        return smsCode;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDO loginByPhone(UserRequest request) throws NoSuchAlgorithmException {
        // 手机号码登录
      /*  UserDO userDO = userDao.findByPhoneAndLoginType(request.getPhone(), "phone");
        if (userDO == null) {
            // 注册用户
            userDO = new UserDO(
                    "灰灰" + CommonUtils.getRandom(10000),
                    request.getPhone(), "phone"
            );
        } else {
            userDO.setLastLoginTime(new Date());
        }
        userDao.save(userDO);*/
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDO loginByOAuth(UserRequest request) {
       /* UserDO userDO = userDao.findByOpenIdAndLoginType(request.getOpenId(), request.getLoginType());
        if (userDO == null) {
            // 注册用户
            userDO = new UserDO(
                    request.getNickname(),
                    request.getLoginType(),
                    request.getOpenId(),
                    request.getHeadImg()
            );
        } else {
            // 更新用户
            userDO.setHeadImg(request.getHeadImg());
            userDO.setName(request.getNickname());
            userDO.setLastLoginTime(new Date());
        }
        userDao.save(userDO);*/
        return null;
    }
}
