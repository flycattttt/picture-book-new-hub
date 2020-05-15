package com.huiuoo.pc.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageGetRequest {

    @NotNull(message = "categoryId不能为空")
    private Long categoryId;
    @NotNull(message = "page不能为空")
    @Min(value = 1,message = "page最小为1")
    private Integer page;
    private Integer limit;
}
