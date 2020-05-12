package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.AdDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
public interface AdDao extends JpaRepository<AdDO,Long> {

    @Query("select a from AdDO a where a.deleted = 1 and a.enabled = 1 and a.startTime<= ?1 and ?1 <= a.endTime")
    List<AdDO> findAllByEffectiveStatus(Date date);
}
