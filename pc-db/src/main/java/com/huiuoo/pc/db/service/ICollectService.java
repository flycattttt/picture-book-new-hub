package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.dataobject.CollectDO;
import com.huiuoo.pc.db.vo.CollectGetRequest;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
public interface ICollectService {

    void add(Long bookId,Long userId) throws BusinessException;

    void del(Long bookId,Long userId) throws BusinessException;

    Integer booleanCollection(Long bookId,Long userId) throws BusinessException;

    List<BookDO> findPageByUserId(CollectGetRequest request, Long userId);

}
