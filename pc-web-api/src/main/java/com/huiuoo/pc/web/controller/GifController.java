package com.huiuoo.pc.web.controller;

import com.huiuoo.pc.common.advice.BaseController;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.response.CommonReturnType;
import com.huiuoo.pc.common.validator.ValidationResult;
import com.huiuoo.pc.common.validator.ValidatorImpl;
import com.huiuoo.pc.db.service.IGifService;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.GifCreateRequest;
import com.huiuoo.pc.db.vo.GifCreateResponse;
import com.huiuoo.pc.db.vo.ImageCreateRequest;
import com.huiuoo.pc.db.vo.ImageCreateResponse;
import com.huiuoo.pc.web.common.jwt.AdminRequest;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@Slf4j
@RequestMapping("gif")
@RestController
public class GifController extends BaseController {

    @Autowired
    private IGifService gifService;

    @Autowired
    private ValidatorImpl validator;

    @PostMapping("create")
    public CommonReturnType create(GifCreateRequest request) throws BusinessException, QiniuException {

        if (request.getFile().isEmpty()){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR,"图片不能为空");
        }
        ValidationResult validate = validator.validate(request);
        if (validate.isHasErrors()){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR,validate.getErrMsg());
        }
        GifCreateResponse response = gifService.createGif(request, AdminRequest.getCurrentAdminId());
        return CommonReturnType.create(response);
    }

}
