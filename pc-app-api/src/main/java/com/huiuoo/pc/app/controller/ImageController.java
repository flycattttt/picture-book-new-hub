package com.huiuoo.pc.app.controller;


import com.huiuoo.pc.common.advice.BaseController;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.response.CommonReturnType;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageGetRequest;
import com.huiuoo.pc.db.vo.ImageIndexResponse;
import com.huiuoo.pc.db.vo.ImageModelResponse;
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
public class ImageController extends BaseController {

    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * @Description: 获取图片目录
     * @param: type 背景：1 ； 元素：2 ； 模型：3
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("/materialType/list")
    public CommonReturnType getMaterialType(Integer type) {

        ImageIndexResponse response = imageService.getMaterialTypeByType(type);
        return CommonReturnType.create(response);
    }

    /**
     * @Description: 分页获取分类下的图片
     * @param:
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("/list")
    public CommonReturnType getImageList(@Valid ImageGetRequest request) {

        List<ImageDO> imageList = imageService.getImageList(request);
        return CommonReturnType.create(imageList);
    }

    /**
     * @Description: 获取模型图片
     * @param: type：3
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("/model/list")
    public CommonReturnType getImageModelList(@Valid ImageGetRequest request) {
        List<ImageModelResponse> responseList = imageService.getImageModelList(request);
        return CommonReturnType.create(responseList);
    }

}
