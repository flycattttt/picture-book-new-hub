package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.vo.ImageCreateRequest;
import com.huiuoo.pc.db.vo.ImageTagGetResponse;
import com.qiniu.common.QiniuException;

import java.util.List;

/**
 * @项目名称：picture-book-app-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/2
 * @version：V1.0
 */

public interface IImageService {


    /** 描述： 根据图片一级类别查找  */
    List<ImageTagGetResponse> findByMainType(Integer mainType) throws BusinessException;

    /** 描述： 根据图片一级类别和标签查找  */
    List<ImageDO> findByMainTypeAndTag(Integer mainType, String imgTag) throws BusinessException;

    /** 描述： 新增图片 */
    ImageDO insertImage(ImageCreateRequest request, Long userId) throws QiniuException, BusinessException;

}
