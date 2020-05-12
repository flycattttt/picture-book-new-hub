package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.vo.PraiseGetRequest;
import com.huiuoo.pc.db.vo.PraiseRequest;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
public interface IPraiseService {

    void add(PraiseRequest request, Long userId) throws BusinessException;

    void del(PraiseRequest request,Long userId);

    Integer booleanPraise(Long bookId,Long userId);

    List<BookDO> findPageByUserId(PraiseGetRequest request, Long userId);
}
