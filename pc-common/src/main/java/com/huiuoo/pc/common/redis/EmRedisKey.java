package com.huiuoo.pc.common.redis;

import lombok.Getter;

@Getter
public enum EmRedisKey {

    USER_MESSAGE_CODE("huiuoo:user:smsCode","短信验证码"),
    IMAGE_LIST("huiuoo:image:image","所有的静态图片"),
    IMAGE_TAG_LIST("huiuoo:image:imageTag","根据图片标签和类别存储图片");

    private String key;
    private String value;

    EmRedisKey(String key,String value){
        this.key = key;
        this.value = value;
    }

}
