package com.huiuoo.pc.app.controller;

import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.db.dataobject.AdDO;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.dataobject.TypeDO;
import com.huiuoo.pc.db.service.IAdService;
import com.huiuoo.pc.db.service.IBookService;
import com.huiuoo.pc.db.service.ITypeService;
import com.huiuoo.pc.db.vo.BookGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/11
 */
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IAdService adService;

    /**
     * @Description:
     * 2.首页显示广告和绘本
     * 2.绘本显示评分大于1的
     * @param: number显示绘本的数量
     * @return:
     */
    @IgnoreJwtVerify
    @GetMapping("list")
    public Map<String,Object> type(Integer number) throws BusinessException {
        if (number == null || number<0){ throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR); }
        Map<String,Object> map = new ConcurrentHashMap<>();
        List<TypeDO> typeDOS = typeService.findAll();
        BookGetRequest request = new BookGetRequest(typeDOS.get(0).getId(), 1,number);
        List<BookDO> bookFirstList = bookService.findPageByTypeId(request);
        List<AdDO> adDOList = adService.findAll();

        // 分类数据
        map.put("types",typeDOS);
        // 绘本数据
        map.put("bookFirstList",bookFirstList);
        // 广告数据
        map.put("adList",adDOList);
        return map;
    }
}
