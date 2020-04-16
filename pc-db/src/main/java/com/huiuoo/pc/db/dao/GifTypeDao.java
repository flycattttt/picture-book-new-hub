package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.GifTypeDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/16
 * @version：V1.0
 */
public interface GifTypeDao extends JpaRepository<GifTypeDO,Long> {


    GifTypeDO findByTypeNameAndModelName(String typeName,String modelName);

    List<GifTypeDO> findAllByDelete(Integer delete);
}
