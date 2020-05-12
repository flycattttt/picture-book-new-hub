package com.huiuoo.pc.web.vo;

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
 * @创建人：lhf
 * @创建时间：2020/4/18
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "名字不能为空")
    private String name;
    @NotNull(message = "年龄不能为空")
    @Min(value = 3,message = "年龄不能小于3")
    @Max(value = 15,message = "年龄不能大于15")
    private Integer age;

}
