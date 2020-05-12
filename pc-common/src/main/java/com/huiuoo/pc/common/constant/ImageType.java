package com.huiuoo.pc.common.constant;

import lombok.Getter;

/**
 * 图片类型
 */
@Getter
public enum ImageType {

    BACKGROUND(1, "背景"),
    ELEMENT(2, "元素"),
    MODEL(3, "模型");

    private int type;
    private String desc;

    ImageType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
