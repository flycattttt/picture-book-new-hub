package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.CategoryDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
public interface CategoryDao extends JpaRepository<CategoryDO,Long> {
    @Query(value = "SELECT c FROM CategoryDO c , CategoryMaterialDO m\n" +
            "where c.id = m.categoryId\n" +
            "and m.type = ?1\n" +
            "and c.name = ?2\n" +
            "and c.pid = ?3")
    CategoryDO findByMaterialTypeAndName(Integer type,String name,Long pid);

    List<CategoryDO> findAllByIdIn(List<Long> ids);
}
