package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.CoverDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/20
 */
public interface CoverDao extends JpaRepository<CoverDO,Long> {
}
