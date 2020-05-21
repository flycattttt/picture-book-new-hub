package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.vo.ImageGetRequest;
import com.huiuoo.pc.db.vo.ImageTagGetRequest;
import com.huiuoo.pc.db.vo.ImageUploadRequest;
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

    ImageDO uploadImage(ImageUploadRequest request) throws BusinessException, QiniuException;

    List<ImageDO> findPageByCategoryId(ImageGetRequest request);

    List<ImageDO> findPageByTags(ImageTagGetRequest request);

    List<ImageDO> findPageAll(Integer page,Integer limit) throws BusinessException;
}
