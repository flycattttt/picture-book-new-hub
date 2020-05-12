package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.ImageCategoryDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
public interface ImageCategoryDao extends JpaRepository<ImageCategoryDO,Long> {

    List<ImageCategoryDO> findAllByCategoryId(Long cId);
}
