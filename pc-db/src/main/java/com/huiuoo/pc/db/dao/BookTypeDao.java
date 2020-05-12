package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.BookTypeDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/11
 */
public interface BookTypeDao extends JpaRepository<BookTypeDO,Long> {

    List<BookTypeDO> findAllByTypeId(Long typeId);
}
