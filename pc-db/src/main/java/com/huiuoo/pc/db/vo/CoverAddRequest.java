package com.huiuoo.pc.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverAddRequest {

    private MultipartFile imgFile;
    @NotNull(message = "字体id不能为空")
    private Long fontId;
    private String template;

}
