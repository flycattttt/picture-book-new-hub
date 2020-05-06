package com.huiuoo.pc.db.vo;

import com.huiuoo.pc.db.dataobject.ImageDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/15
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageIndexResponse {

    private List<String> types;
    private List<ImageDO> images;
}
