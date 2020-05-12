package com.huiuoo.pc.common.constant;

import lombok.Getter;

@Getter
public enum BookScore {

    /** 显示在app中的绘本，分数要大于1 */

    MIN(1, "最小显示分数");

    private Integer score;
    private String desc;

    BookScore(Integer score, String desc) {
        this.score = score;
        this.desc = desc;
    }
}

