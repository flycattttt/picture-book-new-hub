package com.huiuoo.pc.web.controller;

import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.web.vo.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @项目名称：picture-book-new
 * @类描述：测试项目接口是否能正常访问
 * @类创建人：lhf
 * @类创建时间：2020/5/11
 */
@RequestMapping("test")
@Controller
public class TestController {

    @IgnoreJwtVerify
    @ResponseBody
    @GetMapping("index")
    public String index() {

        return "hello world";
    }

}
