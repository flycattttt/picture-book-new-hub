package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.RecommendDO;
import com.huiuoo.pc.db.vo.RecommAddRequest;
import com.huiuoo.pc.db.vo.RecommGetResponse;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/18
 */
public interface IRecommendService {

    RecommendDO add(RecommAddRequest request) throws BusinessException;

    List<RecommGetResponse> recommendImage(Integer number) throws BusinessException;
}


