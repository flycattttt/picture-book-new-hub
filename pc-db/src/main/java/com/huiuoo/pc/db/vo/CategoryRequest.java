package com.huiuoo.pc.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    @NotNull(message = "type不能为空")
    private Integer type;
    @NotBlank(message = "name不能为空")
    private String name;
    @NotNull(message = "pid不能为空")
    private Long pid;
}
