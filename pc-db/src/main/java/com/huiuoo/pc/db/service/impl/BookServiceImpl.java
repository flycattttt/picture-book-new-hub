package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.constant.BookScore;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.db.dao.BookDao;
import com.huiuoo.pc.db.dao.BookTypeDao;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.dataobject.BookTypeDO;
import com.huiuoo.pc.db.service.IBookService;
import com.huiuoo.pc.db.vo.BookCreateRequest;
import com.huiuoo.pc.db.vo.BookGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @类描述：绘本
 * @创建人：lhf
 * @创建时间：2020/5/11
 */
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookTypeDao bookTypeDao;

    @Override
    public List<BookDO> findPageByTypeId(BookGetRequest request) {

        List<BookTypeDO> bookTypeDOS = bookTypeDao.findAllByTypeId(request.getTypeId());
        //排序条件
        Sort sort = new Sort(Sort.Direction.DESC, "score");
        Pageable pageable = PageRequest.of(request.getPage()-1,request.getLimit(), sort);
        List<Long> longList = bookTypeDOS.stream().map(BookTypeDO::getBookId).collect(Collectors.toList());
        return bookDao.findPageByScoreGreaterThanEqualAndIdIn(BookScore.MIN.getScore(),longList,pageable);
    }

    @Override
    public BookDO addBook(BookCreateRequest request, Long userId) {
        BookDO bookDO = new BookDO(userId,request.getCover(),request.getContent(),request.getNickName(),request.getTitle());
        return bookDao.save(bookDO);
    }

    @Override
    public BookDO getBook(Long bookId) throws BusinessException {
        if (null == bookId){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        Optional<BookDO> bookDO = bookDao.findById(bookId);

        if (!bookDO.isPresent()){
            throw new BusinessException(EmBusinessError.CAN_NOT_FIND_RECORD);
        }
        return bookDO.get();
    }
}
