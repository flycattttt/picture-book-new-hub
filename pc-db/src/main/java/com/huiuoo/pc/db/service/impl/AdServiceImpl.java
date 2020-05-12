package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.db.dao.AdDao;
import com.huiuoo.pc.db.dataobject.AdDO;
import com.huiuoo.pc.db.service.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/12
 */
@Service
public class AdServiceImpl implements IAdService {

    @Autowired
    private AdDao adDao;


    @Override
    public List<AdDO> findAll() {
        return adDao.findAllByEffectiveStatus(new Date());
    }
}
