package com.huiuoo.pc.app.controller;


import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@RequestMapping("/image")
@RestController
public class ImageController {

    @Autowired
    private IImageService imageService;

    /**
     * @Description: 分页获取图片
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("/list")
    public List<ImageDO> getByCategory(@Valid ImageGetRequest request) {
        return imageService.findPageByCategoryId(request);
    }

}
