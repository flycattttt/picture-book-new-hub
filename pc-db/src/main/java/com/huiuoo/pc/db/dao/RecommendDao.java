package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.RecommendDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/18
 */
public interface RecommendDao extends JpaRepository<RecommendDO,Long> {

    @Query(value = "SELECT image_id FROM pb_recommend GROUP BY image_id HAVING count(id) >= ?1",nativeQuery = true)
    List<Long> findGroupByImageId(Integer count);

    List<RecommendDO> findAllByImageId(Long imageId, Pageable pageable);
}
