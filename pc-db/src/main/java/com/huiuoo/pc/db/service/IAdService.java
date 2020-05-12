package com.huiuoo.pc.db.service;

import com.huiuoo.pc.db.dataobject.AdDO;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
public interface IAdService {

    List<AdDO> findAll();
}
