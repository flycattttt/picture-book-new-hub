package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.PraiseDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
public interface PraiseDao extends JpaRepository<PraiseDO,Long> {

    PraiseDO findByBookIdAndUserId(Long bookId,Long userId);

    List<PraiseDO> findAllByUserIdAndIsPraise(Long userId, Integer praise, Pageable pageable);
}
