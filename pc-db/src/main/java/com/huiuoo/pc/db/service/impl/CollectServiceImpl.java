package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.constant.BookScore;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.db.dao.BookDao;
import com.huiuoo.pc.db.dao.CollectDao;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.dataobject.CollectDO;
import com.huiuoo.pc.db.dataobject.PraiseDO;
import com.huiuoo.pc.db.service.ICollectService;
import com.huiuoo.pc.db.vo.CollectGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
@Service
public class CollectServiceImpl implements ICollectService {

    @Autowired
    private CollectDao collectDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public void add(Long bookId, Long userId) throws BusinessException {
        if (null == bookId){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        CollectDO collectDO = collectDao.findByBookIdAndUserId(bookId, userId);
        if (collectDO!=null){
            return;
        }
        Optional<BookDO> bookDO = bookDao.findById(bookId);
        if (!bookDO.isPresent()){
            throw new BusinessException(EmBusinessError.CAN_NOT_FIND_RECORD);
        }
        collectDao.save(new CollectDO(bookId,userId));
    }

    @Override
    public void del(Long bookId, Long userId) throws BusinessException {
        if (null == bookId){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        CollectDO collectDO = collectDao.findByBookIdAndUserId(bookId, userId);
        if (collectDO!=null){
            collectDao.deleteById(collectDO.getId());
        }
    }

    @Override
    public Integer booleanCollection(Long bookId, Long userId) throws BusinessException {
        if (null == bookId){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        CollectDO collectDO = collectDao.findByBookIdAndUserId(bookId, userId);
        return collectDO == null ? 0 : 1;
    }

    @Override
    public List<BookDO> findPageByUserId(CollectGetRequest request, Long userId) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(request.getPage()-1,request.getLimit(),sort);
        List<Long> bookIds = collectDao.findAllByUserId(userId,pageable).stream().map(CollectDO::getBookId).collect(Collectors.toList());
        return bookDao.findPageByScoreGreaterThanEqualAndIdIn(BookScore.MIN.getScore(),bookIds,null);
    }
}
