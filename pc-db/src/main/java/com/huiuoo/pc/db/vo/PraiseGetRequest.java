package com.huiuoo.pc.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PraiseGetRequest {
    @NotNull(message = "praise不能为空")
    private Integer praise;
    @NotNull(message = "page不能为空")
    @Min(value = 1, message = "page最小为1")
    private Integer page;
    @NotNull(message = "limit不能为空")
    @Min(value = 0, message = "limit最小为0")
    private Integer limit;
}
