package com.huiuoo.pc.db.service;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.vo.GifCreateRequest;
import com.huiuoo.pc.db.vo.GifCreateResponse;
import com.huiuoo.pc.db.vo.GifGetResponse;
import com.qiniu.common.QiniuException;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/16
 * @version：V1.0
 */
public interface IGifService {

    /** 创建gif文件 */
    GifCreateResponse createGif(GifCreateRequest request, Long userId) throws QiniuException, BusinessException;

    /** 查找gif文件 */
    List<GifGetResponse> findAllGif();

}
