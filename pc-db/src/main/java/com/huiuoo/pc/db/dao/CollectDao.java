package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.CollectDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
public interface CollectDao extends JpaRepository<CollectDO,Long> {

    CollectDO findByBookIdAndUserId(Long bookId,Long userId);

    List<CollectDO> findAllByUserId(Long userId, Pageable pageable);
}
