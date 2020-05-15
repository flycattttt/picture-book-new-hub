package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.app.common.jwt.JwtUserInfo;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dao.CollectDao;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.dataobject.CollectDO;
import com.huiuoo.pc.db.service.ICollectService;
import com.huiuoo.pc.db.vo.CollectGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：收藏服务
 * @类创建人：lhf
 * @类创建时间：2020/5/11
 */
@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    private ICollectService collectService;

    /**
     * @Description: 查看收藏的绘本列表
     * @param:
     * @return:
     */
    @GetMapping("list")
    public List<BookDO> list(@Valid CollectGetRequest request){
        return collectService.findPageByUserId(request, JwtUserInfo.getCurrentUserId());
    }
    /**
     * @Description: 收藏绘本
     * @param:
     * @return:
     */
    @PostMapping("add")
    public void add(Long bookId) throws BusinessException {
        collectService.add(bookId,JwtUserInfo.getCurrentUserId());
    }

    /**
     * @Description: 取消收藏
     * @param:
     * @return:
     */
    @PostMapping("del")
    public void del(Long bookId) throws BusinessException {
        collectService.del(bookId, JwtUserInfo.getCurrentUserId());
    }

}
