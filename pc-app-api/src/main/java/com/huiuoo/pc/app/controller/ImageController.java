package com.huiuoo.pc.app.controller;


import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageGetRequest;
import com.huiuoo.pc.db.vo.ImageTagGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/image")
@RestController
public class ImageController {

    @Autowired
    private IImageService imageService;

    /**
     * @Description: 分类查找图片
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("/list")
    public List<ImageDO> getByCategory(@Valid ImageGetRequest request) {
        return imageService.findPageByCategoryId(request);
    }

    /**
     * @Description: 通过标签查找图片
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("/tags")
    public List<ImageDO> getByTags(@Valid ImageTagGetRequest request) {
        return imageService.findPageByTags(request);
    }
}
