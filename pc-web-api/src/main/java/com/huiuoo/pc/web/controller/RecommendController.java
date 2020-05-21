package com.huiuoo.pc.web.controller;

import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.RecommendDO;
import com.huiuoo.pc.db.service.IRecommendService;
import com.huiuoo.pc.db.vo.RecommAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/18
 */
@RestController
@RequestMapping("recommend")
public class RecommendController {

    @Autowired
    private IRecommendService recommendService;

    /**
     * @Description: 添加相似图片
     * @param:
     * @return:
     */
    @IgnoreJwtVerify
    @PostMapping("add")
    public RecommendDO add(@Valid RecommAddRequest request) throws BusinessException {
        return recommendService.add(request);
    }

}
