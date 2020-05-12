package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.constant.LoginType;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.db.redis.EmRedisKey;
import com.huiuoo.pc.db.redis.RedisUtil;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.common.utils.JuheApi;
import com.huiuoo.pc.db.dao.UserDao;
import com.huiuoo.pc.db.dao.UserLoginDao;
import com.huiuoo.pc.db.dataobject.UserDO;
import com.huiuoo.pc.db.dataobject.UserLoginDO;
import com.huiuoo.pc.db.service.IUserService;
import com.huiuoo.pc.db.vo.UserLoginRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserLoginDao userOauthDao;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void sendSms(String phone) throws BusinessException {

        // 手机号码格式验证
        CommonUtils.validatorPhone(phone);

        Object temporary = redisUtil.hget(EmRedisKey.USER_MESSAGE_CODE.getKey() + "_temporary", phone);
        if (temporary != null) {
            throw new BusinessException(EmBusinessError.SEND_MESSAGE_CODE_FORBID);
        }
        String smsCode = CommonUtils.getSmsCode();
        JuheApi.sendValidationCode(phone, smsCode);
        // 同一手机号码60秒内不能重复发送
        redisUtil.hset(EmRedisKey.USER_MESSAGE_CODE.getKey() + "_temporary", phone, 1, 60);
        // 验证码有效期1000分钟
        redisUtil.hset(EmRedisKey.USER_MESSAGE_CODE.getKey(), phone, smsCode, 60000);
    }

    @SuppressWarnings("all")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDO loginByIdentity(UserLoginRequest request) throws BusinessException {

        // 手机号码登录需要验证验证码
        if (request.getLoginType() == null){
            // 手机号码登录参数
            if (!request.phoneValidate()) {
                throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
            }
            //校验短信验证码
            String code = (String) redisUtil.hget(EmRedisKey.USER_MESSAGE_CODE.getKey(), request.getIdentity());
            if (!StringUtils.equals(code, request.getSmsCode())) {
                throw new BusinessException(EmBusinessError.SMSCODE_IS_ERROR);
            }
            // 登录方式为手机
            request.setLoginType(LoginType.PHONE.getType());
        }else {
            if (!request.oauthValidate()){
                throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
            }
        }

        // 查询数据
        UserLoginDO oldUserLoginDO = userOauthDao.findByLoginTypeAndIdentity(request.getLoginType(), request.getIdentity());
        UserLoginDO newUserOauthDO;
        UserDO userDO;

        // 判断user_login表是否存在该条信息
        if (oldUserLoginDO == null) {
            oldUserLoginDO = new UserLoginDO();
            oldUserLoginDO.setLoginType(request.getLoginType());
            oldUserLoginDO.setIdentity(request.getIdentity());
            // 添加用户
            if (StringUtils.isNotBlank(request.getNickName()) && StringUtils.isNotBlank(request.getHeadImg())) {
                userDO = new UserDO(request.getNickName(), request.getHeadImg());
            } else {
                userDO = new UserDO(CommonUtils.randomName(), "");
            }
            userDao.save(userDO);
            oldUserLoginDO.setUserId(userDO.getId());
            userOauthDao.save(oldUserLoginDO);
        } else {
            // login存在，就通过userId查找用户
            Optional<UserDO> optionalUserDO = userDao.findById(oldUserLoginDO.getUserId());
            if (optionalUserDO.isPresent()) {
                // 用户已存在就更新信息
                userDO = optionalUserDO.get();
                userDO.setLastLoginTime(new Date());
                if (StringUtils.isNotBlank(request.getNickName()) && StringUtils.isNotBlank(request.getHeadImg())) {
                    userDO.setAvatar(request.getHeadImg());
                    userDO.setName(request.getNickName());
                }
                userDao.save(userDO);
            } else {
                // 不存在当前用户就创建用户
                // 如果是第三方登录，用户名和头像不为空，手机号登录为用户生成昵称
                if (StringUtils.isNotBlank(request.getNickName()) && StringUtils.isNotBlank(request.getHeadImg())) {
                    userDO = new UserDO(request.getNickName(), request.getHeadImg());
                } else {
                    userDO = new UserDO(CommonUtils.randomName(), "");
                }
                userDao.save(userDO);
                oldUserLoginDO.setUserId(userDO.getId());
                userOauthDao.save(oldUserLoginDO);
            }
        }
        return userDO;
    }

}
