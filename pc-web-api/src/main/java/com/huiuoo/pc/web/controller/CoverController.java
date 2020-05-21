package com.huiuoo.pc.web.controller;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.CoverDO;
import com.huiuoo.pc.db.service.ICoverService;
import com.huiuoo.pc.db.vo.CoverAddRequest;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @PostMapping("add")
    public CoverDO add(@Valid CoverAddRequest request) throws QiniuException, BusinessException {
       return coverService.add(request);
    }
}
