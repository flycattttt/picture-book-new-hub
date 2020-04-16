package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.ImageTypeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/15
 * @version：V1.0
 */
public interface ImageTypeDao extends JpaRepository<ImageTypeDO,Long> {

    List<ImageTypeDO> findAllByMainTypeAndDelete(Integer mainType,Integer delete);

    ImageTypeDO findByMainTypeAndFollowType(Integer mainType,String followType);
}
