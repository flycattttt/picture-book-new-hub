package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.vo.BookCreateRequest;
import com.huiuoo.pc.db.vo.BookGetRequest;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/11
 */
public interface IBookService {

    List<BookDO> findPageByTypeId(BookGetRequest request);

    BookDO addBook(BookCreateRequest request,Long userId);

    BookDO getBook(Long bookId) throws BusinessException;
}
