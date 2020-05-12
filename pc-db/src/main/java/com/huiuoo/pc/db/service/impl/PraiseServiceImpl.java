package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.constant.BookScore;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.db.dao.BookDao;
import com.huiuoo.pc.db.dao.PraiseDao;
import com.huiuoo.pc.db.dataobject.BookDO;
import com.huiuoo.pc.db.dataobject.PraiseDO;
import com.huiuoo.pc.db.service.IPraiseService;
import com.huiuoo.pc.db.vo.CollectGetRequest;
import com.huiuoo.pc.db.vo.PraiseGetRequest;
import com.huiuoo.pc.db.vo.PraiseRequest;
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
public class PraiseServiceImpl implements IPraiseService {

    @Autowired
    private PraiseDao praiseDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public void add(PraiseRequest request, Long userId) throws BusinessException {
        Optional<BookDO> bookDO = bookDao.findById(request.getBookId());
        if (!bookDO.isPresent()){
            throw new BusinessException(EmBusinessError.CAN_NOT_FIND_RECORD);
        }
        PraiseDO praiseDO = praiseDao.findByBookIdAndUserId(request.getBookId(), userId);
        if (praiseDO != null) {
            return;
        }
        praiseDao.save(new PraiseDO(request.getBookId(), userId, request.getPraise()));
    }

    @Override
    public void del(PraiseRequest request, Long userId) {
        PraiseDO praiseDO = praiseDao.findByBookIdAndUserId(request.getBookId(), userId);
        if (praiseDO != null) {
            praiseDao.deleteById(praiseDO.getId());
        }
    }

    @Override
    public Integer booleanPraise(Long bookId, Long userId) {
        PraiseDO praiseDO = praiseDao.findByBookIdAndUserId(bookId, userId);
        return praiseDO == null ? 0 : praiseDO.getIsPraise();
    }

    @Override
    public List<BookDO> findPageByUserId(PraiseGetRequest request, Long userId) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(request.getPage()-1,request.getLimit(),sort);
        List<Long> bookIds = praiseDao.findAllByUserIdAndIsPraise(userId, request.getPraise(),pageable).stream().map(PraiseDO::getBookId).collect(Collectors.toList());
        return bookDao.findPageByScoreGreaterThanEqualAndIdIn(BookScore.MIN.getScore(),bookIds,null);
    }
}
