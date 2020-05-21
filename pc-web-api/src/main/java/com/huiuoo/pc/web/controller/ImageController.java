package com.huiuoo.pc.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.constant.ImageType;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.utils.QiniuUtils;
import com.huiuoo.pc.db.dataobject.CategoryDO;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.service.ICategoryService;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.CategoryRequest;
import com.huiuoo.pc.db.vo.ImageGetRequest;
import com.huiuoo.pc.db.vo.ImageUploadRequest;
import com.huiuoo.pc.web.common.jwt.JwtAdminInfo;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：图片服务
 * @类创建人：lhf
 * @类创建时间：2020/5/11
 */
@Slf4j
@RequestMapping("image")
@RestController
public class ImageController {


    private final ICategoryService categoryService;

    private final IImageService imageService;

    public ImageController(IImageService imageService, ICategoryService categoryService) {
        this.imageService = imageService;
        this.categoryService = categoryService;
    }

    /**
     * @Description: 上传图片
     * @param:
     * @return:
     */
    @PostMapping("upload")
    public ImageDO create(@Valid ImageUploadRequest request) throws BusinessException, QiniuException {
        return imageService.uploadImage(request);
    }

    /**
     * @Description: [TODO]临时提供下载全部图片
     * @param:
     * @return:
     */
    @RequestMapping("download")
    public void download(Integer type) throws BusinessException {
        List<CategoryDO> materialType = categoryService.findAllByMaterialType(type);
        ImageGetRequest request = new ImageGetRequest();
        request.setPage(1);
        request.setLimit(100000);
        materialType.forEach(m->{
            log.info("下载{}",m.getName());
            request.setCategoryId(m.getId());
            imageService.findPageByCategoryId(request).forEach(i->{
                List<String> list = JSONArray.parseArray(i.getUrls(), String.class);
                QiniuUtils.download(list.get(0),list.get(0));
            });
            log.info("{}下载完毕。",m.getName());
        });
    }

    /**
     * @Description: 分页分类获取图片
     * @param:
     * @return:
     */
    @GetMapping("category")
    public List<ImageDO> category(@Valid ImageGetRequest request){
        return imageService.findPageByCategoryId(request);
    }

    /**
     * @Description: 分页获取图片
     * @param:
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("list")
    public List<ImageDO> list(Integer page,Integer limit) throws BusinessException {
        return imageService.findPageAll(page, limit);
    }

}
