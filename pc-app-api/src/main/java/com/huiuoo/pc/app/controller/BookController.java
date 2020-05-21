package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.app.common.jwt.JwtUserInfo;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.utils.QiniuUtils;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.service.*;
import com.huiuoo.pc.db.vo.BookAddRequest;
import com.huiuoo.pc.db.vo.BookGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @项目名称：picture-book-new
 * @类描述：绘本服务
 * @类创建人：lhf
 * @类创建时间：2020/5/11
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IPraiseService praiseService;

    @Autowired
    private ICollectService collectService;

    /**
     * @Description: 获取分类下的绘本
     * @param:typeId,page,limit
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("list")
    public List<BookDO> list(@Valid BookGetRequest request){
        return bookService.findPageByTypeId(request);
    }

    /**
     * @Description: 获取上传文件凭证
     * @param:
     * @return:
     */
    @GetMapping("getUploadToken")
    public String getUploadToken(){
        return QiniuUtils.getUploadToken();
    }


    /**
     * @Description: 发布绘本
     * @param:typeId,page,limit
     * @return:
     */
    @PostMapping("add")
    public BookDO add(@Valid BookAddRequest request){
        return bookService.addBook(request, JwtUserInfo.getCurrentUserId());
    }


    /**
     * @Description: 绘本详情
     * 1.用户可以不登录。
     * 2.如果用户登录，则返回用户收藏和点赞信息。
     * @param:
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("get")
    public Map get(Long bookId) throws BusinessException {
        Map<String,Object> map = new ConcurrentHashMap<>();
        BookDO book = bookService.getBook(bookId);
        Integer praise = 0;
        Integer collection = 0;
        Long userId = JwtUserInfo.getCurrentUserId();
        if (userId!=null){
            // 0-未操作  1-赞  2-踩
            praise = praiseService.booleanPraise(bookId, userId);
            // 0-未收藏  1-收藏
            collection = collectService.booleanCollection(bookId, userId);
        }
        map.put("book",book);
        map.put("praise",praise);
        map.put("collection",collection);
        return map;
    }
}
