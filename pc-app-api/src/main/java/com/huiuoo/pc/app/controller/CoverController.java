package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.db.dataobject.CoverDO;
import com.huiuoo.pc.db.service.ICoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/20
 */
@RestController
@RequestMapping("cover")
public class CoverController {

    @Autowired
    private ICoverService coverService;


    /**
     * @Description:获取封面模板列表
     * @param:
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("list")
    public List<CoverDO> list(){
       return coverService.findAll();
    }
}
