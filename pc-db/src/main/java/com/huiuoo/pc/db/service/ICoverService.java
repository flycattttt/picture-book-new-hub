package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dao.CoverDao;
import com.huiuoo.pc.db.dataobject.CoverDO;
import com.huiuoo.pc.db.vo.CoverAddRequest;
import com.qiniu.common.QiniuException;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/20
 */
public interface ICoverService {

    List<CoverDO> findAll();

    CoverDO add(CoverAddRequest request) throws BusinessException, QiniuException;

}
