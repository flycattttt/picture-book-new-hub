package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.CategoryDO;
import com.huiuoo.pc.db.vo.CategoryIndexResponse;
import com.huiuoo.pc.db.vo.CategoryRequest;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
public interface ICategoryService {

    CategoryDO addCategory(CategoryRequest request) throws BusinessException;

    List<CategoryDO> findAllByMaterialType(Integer type) throws BusinessException;

    /** app编辑页面获取图片分类及图片数组 */
 //   CategoryIndexResponse getByIndexList(Integer type);
}
