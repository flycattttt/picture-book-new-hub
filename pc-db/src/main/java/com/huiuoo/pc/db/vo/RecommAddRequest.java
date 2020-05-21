package com.huiuoo.pc.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommAddRequest {

    @NotNull(message = "图片id不能为空")
    private Long imageId;

    @NotNull(message = "推荐图片id不能为空")
    private Long recomId;

    @NotNull(message = "相似度similarity不能为空")
    @Min(value = 0 , message = "相似度最小为0")
    @Max(value = 100,message = "相似度最大为100")
    private Integer similarity;
}
