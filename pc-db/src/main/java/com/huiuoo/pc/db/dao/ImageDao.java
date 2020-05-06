package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.ImageDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@Repository
public interface ImageDao extends JpaRepository<ImageDO, Long>, JpaSpecificationExecutor<ImageDO> {

    @Query(value = "SELECT DISTINCT i.materialType FROM ImageDO i where i.type = ?1")
    List<String> findDistinctMaterialTypeByType(Integer type);

    List<ImageDO> findByTypeAndMaterialType(Integer type, String materialType, Pageable pageable);

}
