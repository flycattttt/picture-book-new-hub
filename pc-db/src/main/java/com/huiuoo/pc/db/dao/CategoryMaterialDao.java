package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.CategoryMaterialDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
public interface CategoryMaterialDao extends JpaRepository<CategoryMaterialDO,Long> {

    CategoryMaterialDO findByCategoryId(Long cId);

    List<CategoryMaterialDO> findAllByType(Integer type);

}
