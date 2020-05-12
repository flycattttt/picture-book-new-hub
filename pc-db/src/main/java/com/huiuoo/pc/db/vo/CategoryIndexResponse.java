package com.huiuoo.pc.db.vo;

import com.huiuoo.pc.db.dataobject.CategoryDO;
import com.huiuoo.pc.db.dataobject.ImageDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryIndexResponse {

    private CategoryDO category;
    private List<ImageDO> imageList;

}
