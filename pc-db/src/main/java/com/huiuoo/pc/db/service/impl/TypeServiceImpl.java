package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.db.dao.TypeDao;
import com.huiuoo.pc.db.dataobject.TypeDO;
import com.huiuoo.pc.db.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/11
 */
@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<TypeDO> findAll() {
        return typeDao.findAll();
    }
}
