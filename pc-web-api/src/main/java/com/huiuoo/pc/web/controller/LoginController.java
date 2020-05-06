package com.huiuoo.pc.web.controller;

import com.huiuoo.pc.common.advice.BaseController;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.validator.ValidationResult;
import com.huiuoo.pc.common.validator.ValidatorImpl;
import com.huiuoo.pc.web.vo.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@RequestMapping("login")
@Controller
public class LoginController extends BaseController {

    @IgnoreJwtVerify
    @ResponseBody
    @GetMapping("index")
    public String index(@Valid LoginRequest request) throws BusinessException {

        return "ok";
    }

}
