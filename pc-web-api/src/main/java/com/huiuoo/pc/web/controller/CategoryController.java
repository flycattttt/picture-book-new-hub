package com.huiuoo.pc.web.controller;

import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.db.dataobject.CategoryDO;
import com.huiuoo.pc.db.service.ICategoryService;
import com.huiuoo.pc.db.vo.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：物料类型服务
 * @类创建人：lhf
 * @类创建时间：2020/5/11
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /** 
     * @Description: 
     * @param: type-物料类型（1背景，2元素，3模型）
     * @return:  
     */
    @IgnoreJwtVerify
    @GetMapping("/list")
    public List<CategoryDO> getOrSaveCategory(Integer type) throws BusinessException {
        return categoryService.findAllByMaterialType(type);
    }

    @IgnoreJwtVerify
    @PostMapping("/add")
    public CategoryDO addCategory(@Valid CategoryRequest request) throws BusinessException {
        return categoryService.addCategory(request);
    }

}
