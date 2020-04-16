package com.huiuoo.pc.app.controller;


import com.huiuoo.pc.common.advice.BaseController;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.response.CommonReturnType;
import com.huiuoo.pc.db.service.IGifService;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageResponse;
import com.huiuoo.pc.db.vo.ImageTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@RequestMapping("gif")
@RestController
public class GifController extends BaseController {

    private final IGifService gifService;

    public GifController(IGifService gifService) {
        this.gifService = gifService;
    }

    @IgnoreJwtVerify
    @GetMapping("list")
    public CommonReturnType findAll() {
        return CommonReturnType.create(gifService.findAllGif());
    }
}
