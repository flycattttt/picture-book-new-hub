package com.huiuoo.pc.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
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
public class PraiseRequest {

    @NotNull(message = "bookId不能为空")
    private Long bookId;
    @NotNull(message = "praise不能为空")
    @Min(value = 1,message = "praise最小为1")
    @Max(value = 2,message = "praise最大为2")
    private Integer praise;

}
