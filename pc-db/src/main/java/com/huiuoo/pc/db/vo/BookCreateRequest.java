package com.huiuoo.pc.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest {

    @NotBlank(message = "title不能为空")
    private String title;
    @NotBlank(message = "cover不能为空")
    private String cover;
    @NotBlank(message = "content不能为空")
    private String content;
    @NotBlank(message = "nickName不能为空")
    private String nickName;

}
