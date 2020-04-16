package com.huiuoo.pc.app.controller;


import com.huiuoo.pc.app.common.jwt.JwtUserInfo;
import com.huiuoo.pc.common.advice.BaseController;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.response.CommonReturnType;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageResponse;
import com.huiuoo.pc.db.vo.ImageTypeResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@RequestMapping("image")
@RestController
public class ImageController extends BaseController {

    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @IgnoreJwtVerify
    @GetMapping("get/mainType/{mainType}")
    public CommonReturnType findImage(@PathVariable("mainType") Integer mainType) throws BusinessException {

        List<ImageTypeResponse> responses = imageService.findByMainType(mainType);
        return CommonReturnType.create(responses);
    }

    @IgnoreJwtVerify
    @GetMapping("get")
    public CommonReturnType get(@RequestParam("mainType") Integer mainType,
                                      @RequestParam("imgTag") String imgTag) throws BusinessException {

        List<ImageResponse> responses = imageService.findByMainTypeAndTag(mainType, imgTag);
        return CommonReturnType.create(responses);
    }

}
