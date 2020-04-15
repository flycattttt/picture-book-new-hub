package com.huiuoo.pc.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/14
 * @version：V1.0
 */
@Controller
@RequestMapping("pay")
public class PayController {

    @ResponseBody
    @RequestMapping("order")
    public String payByWeiChat(@RequestBody String param, HttpServletRequest request){
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(JSON.toJSONString(jsonObject));
        return "ok";
    }
}
