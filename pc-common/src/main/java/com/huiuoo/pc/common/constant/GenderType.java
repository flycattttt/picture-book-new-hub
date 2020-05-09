package com.huiuoo.pc.common.constant;

import lombok.Getter;

@Getter
public enum GenderType {

    OTHER(0, "未指定"),
    MAN(1, "男性"),
    WOMAN(2, "女性");

    private Integer type;
    private String desc;

    GenderType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
