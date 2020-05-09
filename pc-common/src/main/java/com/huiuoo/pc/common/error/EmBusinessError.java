package com.huiuoo.pc.common.error;

public enum EmBusinessError implements CommonError {

    SUCCESS(200, "success"),
    //通用错误类型10000
    UN_KNOW_ERROR(10001, "未知错误"),
    REQUEST_PARAM_ERROR(1002, "参数不正确"),
    RESPONSE_ERROR(1003, "响应失败"),
    SEND_MESSAGE_CODE_ERROR(10006, "发送短信验证码发生异常"),
    SEND_MESSAGE_CODE_FORBID(10008, "频繁获取验证码"),
    CAN_NOT_FIND_RECORD(10009, "未找到该实例"),
    //用户错误类型20000
    PHONE_IS_EXIST(20001, "手机号已被注册"),
    STATUS_IS_LOCKOUT(20002, "账号已被锁定"),
    STATUS_TOKEN_INVALID(20003, "token无效"),
    STATUS_IS_ERROR(20004, "token错误"),
    STATUS_TOKEN_TIMEOUT(20005, "token已过期"),
    SMSCODE_IS_ERROR(20006, "验证码错误"),
    OAUTH_IS_EXIST(20007, "授权账户已存在");


    private int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
