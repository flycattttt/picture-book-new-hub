package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.app.common.jwt.JwtUserInfo;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.service.IPraiseService;
import com.huiuoo.pc.db.vo.PraiseGetRequest;
import com.huiuoo.pc.db.vo.PraiseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
@RestController
@RequestMapping("praise")
public class PraiseController {


    @Autowired
    private IPraiseService praiseService;

    /**
     * @Description:查看点赞或踩的绘本列表
     * @param:
     * @return:
     */
    @GetMapping("list")
    public List<BookDO> list(@Valid PraiseGetRequest request){
        return praiseService.findPageByUserId(request, JwtUserInfo.getCurrentUserId());
    }
    /**
     * @Description: 点赞或踩
     * @param: praise 1-赞 2-踩
     * @return:
     */
    @PostMapping("add")
    public void add(@Valid PraiseRequest request) throws BusinessException {
        praiseService.add(request,JwtUserInfo.getCurrentUserId());
    }

    /**
     * @Description: 取消点赞或取消踩
     * @param: praise 1-赞 2-踩
     * @return:
     */
    @PostMapping("del")
    public void del(@Valid PraiseRequest request){
        praiseService.del(request, JwtUserInfo.getCurrentUserId());
    }

}
