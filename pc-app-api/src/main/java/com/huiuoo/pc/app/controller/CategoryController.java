package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.db.dataobject.CategoryDO;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.service.ICategoryService;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.CategoryIndexResponse;
import com.huiuoo.pc.db.vo.CategoryRequest;
import com.huiuoo.pc.db.vo.ImageGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private IImageService imageService;

    /**
     * @Description:  app编辑页面获取图片分类及图片数组
     * @param: type-物料类型（1背景，2元素，3模型） number-每个分类下返回的图片数量
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("/index/list")
    public List<CategoryIndexResponse> indexList(Integer type, Integer number) throws BusinessException {
        if (type == null) {
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        List<CategoryIndexResponse> responseList = new ArrayList<>();

        // 物料类型的子类别
        List<CategoryDO> categoryDOList = categoryService.findAllByMaterialType(type);
        // 查找子类别下的图片并分页
        categoryDOList.forEach(c->{
            CategoryIndexResponse response = new CategoryIndexResponse();
            List<ImageDO> imageDOList = imageService.findPageByCategoryId(new ImageGetRequest(c.getId(),1,number));
            response.setCategory(c);
            response.setImageList(imageDOList);
            responseList.add(response);
        });
        return responseList;
    }

}
