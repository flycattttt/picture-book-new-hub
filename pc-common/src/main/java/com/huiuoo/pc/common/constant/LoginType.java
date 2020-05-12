package com.huiuoo.pc.common.constant;

import lombok.Getter;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/8
 */
@Getter
public enum LoginType {
    PHONE(0, "手机号码"),
    QQ(1, "QQ"),
    WE_CHAT(2, "WX");

    private int type;
    private String desc;

    LoginType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
