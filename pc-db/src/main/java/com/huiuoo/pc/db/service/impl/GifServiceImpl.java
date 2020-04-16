package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.constant.CommonStatus;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.common.utils.QiniuUtils;
import com.huiuoo.pc.db.dao.GifDao;
import com.huiuoo.pc.db.dao.GifTypeDao;
import com.huiuoo.pc.db.dataobject.GifDO;
import com.huiuoo.pc.db.dataobject.GifTypeDO;
import com.huiuoo.pc.db.service.IGifService;
import com.huiuoo.pc.db.vo.GifCreateRequest;
import com.huiuoo.pc.db.vo.GifCreateResponse;
import com.huiuoo.pc.db.vo.GifGetResponse;
import com.qiniu.common.QiniuException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/16
 * @version：V1.0
 */
@Service
public class GifServiceImpl implements IGifService {

    public static final Integer MODEL = 3;

    private final GifTypeDao gifTypeDao;

    private final GifDao gifDao;

    public GifServiceImpl(GifTypeDao gifTypeDao, GifDao gifDao) {
        this.gifTypeDao = gifTypeDao;
        this.gifDao = gifDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GifCreateResponse createGif(GifCreateRequest request, Long userId) throws QiniuException, BusinessException {
        GifTypeDO gifTypeDO = gifTypeDao.findByTypeNameAndModelName(request.getTypeName(), request.getModelName());

        if (gifTypeDO == null) {
            gifTypeDO = new GifTypeDO(
                    request.getTypeName(),
                    request.getModelName()
            );
            gifTypeDao.save(gifTypeDO);
        }

        // 生成gif的url
        String imageUrl = CommonUtils.generateImageUrl(
                Objects.requireNonNull(request.getFile().getOriginalFilename()), MODEL);

        GifDO gifDO = new GifDO();

        gifDO.setUrl(imageUrl);
        gifDO.setGifTypeId(gifDO.getId());
        gifDO.setDescription(StringUtils.isBlank(request.getDescription()) ? "" : request.getDescription());
        gifDO.setSize(request.getFile().getSize());
        gifDO.setAdminId(userId);
        gifDO.setCreateTime(new Date());
        gifDO.setUpdateTime(gifDO.getCreateTime());
        gifDO.setDelete(CommonStatus.VALID.getStatus());
        gifDao.save(gifDO);

        // 上传图片至七牛云
        QiniuUtils.uploadImage(request.getFile(), imageUrl);
        return new GifCreateResponse(gifDO.getId(), gifDO.getUrl());
    }

    @Override
    public List<GifGetResponse> findAllGif() {
        List<GifTypeDO> typeDOS = gifTypeDao.findAllByDelete(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(typeDOS)) {
            return null;
        }

        List<GifGetResponse> responseList = new ArrayList<>();
        typeDOS.stream()
                // 根据gifType的typeName对查询到的数据进行分组
                .collect(Collectors
                        .groupingBy(GifTypeDO::getTypeName, Collectors.toList()))

                // 动图类别
                .forEach((t, m) -> {
                    GifGetResponse response = new GifGetResponse();
                    response.setTypeName(t);
                    List<GifGetResponse.ModelVO> modelVOList = new ArrayList<>();

                    // 模型类别
                    m.forEach(n -> {
                        GifGetResponse.ModelVO modelVO = new GifGetResponse.ModelVO();
                        modelVO.setModelName(n.getModelName());

                        List<GifGetResponse.GifVO> gifVOList = new ArrayList<>();
                        // 动图
                        n.getGifDOList()
                                .forEach(g -> {
                                    GifGetResponse.GifVO gifVO = new GifGetResponse.GifVO();
                                    gifVO.setGifId(g.getId());
                                    gifVO.setGifUrl(g.getUrl());
                                    gifVO.setGifDesc(g.getDescription());
                                    gifVOList.add(gifVO);
                                });
                        modelVO.setGifVOList(gifVOList);
                        modelVOList.add(modelVO);
                    });
                    response.setModelVOList(modelVOList);
                    responseList.add(response);
                });

        return responseList;
    }
}
