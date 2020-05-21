package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.service.IRecommendService;
import com.huiuoo.pc.db.vo.RecommGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
     * @Description: 底图推荐
     * @param: number指最终获取的组数
     * @return: 1.每组4张图
     *          2.每组中的4张图保持相似
     *          3.每组数据先后顺序不固定
     */
    @GetMapping("list")
    @IgnoreJwtVerify
    public List<RecommGetResponse> list(Integer number) throws BusinessException {
       return recommendService.recommendImage(number);
    }
}
