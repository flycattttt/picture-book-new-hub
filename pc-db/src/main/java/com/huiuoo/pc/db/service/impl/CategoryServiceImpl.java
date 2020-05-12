package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.db.dao.CategoryDao;
import com.huiuoo.pc.db.dao.CategoryMaterialDao;
import com.huiuoo.pc.db.dataobject.CategoryDO;
import com.huiuoo.pc.db.dataobject.CategoryMaterialDO;
import com.huiuoo.pc.db.service.ICategoryService;
import com.huiuoo.pc.db.vo.CategoryRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryMaterialDao materialDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CategoryDO addCategory(CategoryRequest request) {
        CategoryDO categoryDO = categoryDao.findByMaterialTypeAndName(request.getType(), request.getName(),request.getPid());
        if (categoryDO == null) {
            categoryDO = new CategoryDO();
            categoryDO.setPid(request.getPid());
            categoryDO.setHierarchy(String.format("%S%S", String.valueOf(request.getPid()), "-"));
            categoryDO.setName(request.getName());
            categoryDao.save(categoryDO);

            CategoryMaterialDO categoryMaterialDO = new CategoryMaterialDO();
            categoryMaterialDO.setType(request.getType());
            categoryMaterialDO.setCategoryId(categoryDO.getId());
            materialDao.save(categoryMaterialDO);
        }
        return categoryDO;
    }

    @Override
    public List<CategoryDO> findAllByMaterialType(Integer type) throws BusinessException {
        if (type == null) {
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        List<CategoryMaterialDO> materialDOS = materialDao.findAllByType(type);
        if (CollectionUtils.isEmpty(materialDOS)) {
            return Collections.emptyList();
        }

        List<Long> idList = materialDOS.stream()
                .map(CategoryMaterialDO::getCategoryId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }

        return categoryDao.findAllByIdIn(idList);
    }
}
