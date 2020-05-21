package com.huiuoo.pc.db.vo;

import com.huiuoo.pc.db.dataobject.ImageDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommGetResponse {

    private List<ImageDO> imageList;
}
