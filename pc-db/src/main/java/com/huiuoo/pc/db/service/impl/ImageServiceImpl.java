package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.constant.CommonStatus;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.common.utils.QiniuUtils;
import com.huiuoo.pc.db.dao.ImageDao;
import com.huiuoo.pc.db.dao.ImageTagDao;
import com.huiuoo.pc.db.dao.ImageTypeDao;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.dataobject.ImageTagDO;
import com.huiuoo.pc.db.dataobject.ImageTypeDO;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageCreateRequest;
import com.huiuoo.pc.db.vo.ImageCreateResponse;
import com.huiuoo.pc.db.vo.ImageResponse;
import com.huiuoo.pc.db.vo.ImageTypeResponse;
import com.qiniu.common.QiniuException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @项目名称：picture-book-app-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/2
 * @version：V1.0
 */
@Service
public class ImageServiceImpl implements IImageService {

    private final ImageDao imageDao;

    private final ImageTagDao imageTagDao;

    private final ImageTypeDao imageTypeDao;

    public ImageServiceImpl(ImageDao imageDao, ImageTagDao imageTagDao, ImageTypeDao imageTypeDao) {
        this.imageDao = imageDao;
        this.imageTagDao = imageTagDao;
        this.imageTypeDao = imageTypeDao;
    }

    @Override
    public List<ImageTypeResponse> findByMainType(Integer mainType) throws BusinessException {
        if (mainType==null || mainType==0){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR, "一级类别不能为空");
        }
        // 查询正在使用的类别
        List<ImageTypeDO> imageTypeDOS = imageTypeDao.findAllByMainTypeAndDelete(mainType
                ,CommonStatus.VALID.getStatus());
        List<ImageTypeResponse> responseList = new ArrayList<>();

        // 图片类别
        imageTypeDOS.forEach(i->{
            ImageTypeResponse response = new ImageTypeResponse();
            response.setTypeId(i.getId());
            response.setFollowType(i.getFollowType());
            List<ImageTypeResponse.ImageVO> imageVOList = new ArrayList<>();
            // 图片
            i.getImageDOS().forEach(d->{
                ImageTypeResponse.ImageVO imageVO = new ImageTypeResponse.ImageVO();
                // 获取有效状态图片
                if (d.getDelete().equals(CommonStatus.VALID.getStatus())){
                    imageVO.setImageId(d.getId());
                    imageVO.setDescription(d.getDescription());
                    imageVO.setUrl(d.getUrl());
                    List<ImageTypeResponse.TagVO> tagVOList = new ArrayList<>();
                    // 图片标签
                    d.getImageTagDOS().forEach(t->{
                        ImageTypeResponse.TagVO tagVO = new ImageTypeResponse.TagVO();
                        tagVO.setTagId(t.getId());
                        tagVO.setTag(t.getImgTag());
                        tagVOList.add(tagVO);
                    });
                    imageVO.setTagVOList(tagVOList);
                    imageVOList.add(imageVO);
                }
            });
            response.setImageVOList(imageVOList);
        });
        return responseList;
    }
    @Override
    public List<ImageResponse> findByMainTypeAndTag(Integer mainType, String imgTag) throws BusinessException {
        if (mainType==null || mainType==0){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR, "一级类别不能为空");
        }
        if (StringUtils.isBlank(imgTag)){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR, "标签不能为空");
        }

        List<Long> tag = imageTagDao.findImageIdByTag(imgTag);
        if (CollectionUtils.isEmpty(tag)){
            return null;
        }
        List<ImageDO> imageDOList = imageDao.findAllByImageTypeIdAndIdIn(mainType, tag);
        if (CollectionUtils.isEmpty(imageDOList)){
            return null;
        }

        List<ImageResponse> responseList = new ArrayList<>();
        imageDOList.forEach(i->{
            ImageResponse response = new ImageResponse();
            BeanUtils.copyProperties(i,response);
            responseList.add(response);
        });
        return responseList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImageCreateResponse createImage(ImageCreateRequest request, Long userId) throws QiniuException, BusinessException {

        // 判断类别是否存在
        ImageTypeDO imageTypeDO = imageTypeDao.findByMainTypeAndFollowType(request.getMainType()
                , request.getFollowType());

        if (imageTypeDO == null){
            imageTypeDO = new ImageTypeDO(
                    request.getMainType(),
                    request.getFollowType()
            );
            imageTypeDao.save(imageTypeDO);
        }

        // 生成图片url名称
        String imageUrl = CommonUtils.generateImageUrl(
                Objects.requireNonNull(request.getFile().getOriginalFilename()), request.getMainType());

        ImageDO imageDO = new ImageDO();

        imageDO.setUrl(imageUrl);
        imageDO.setImageTypeId(imageTypeDO.getId());
        imageDO.setDescription(StringUtils.isBlank(request.getDescription())?"":request.getDescription());
        imageDO.setSize(request.getFile().getSize());
        imageDO.setAdminId(userId);
        imageDO.setCreateTime(new Date());
        imageDO.setUpdateTime(imageDO.getCreateTime());
        imageDO.setDelete(CommonStatus.VALID.getStatus());
        ImageDO saveImage = imageDao.save(imageDO);

        if (CollectionUtils.isNotEmpty(request.getTagList())) {
            // 去除重复标签
            List<String> uniqueTag = new ArrayList<>(new HashSet<>(request.getTagList()));
            List<ImageTagDO> imageTagDOS = new ArrayList<>();
            uniqueTag.forEach(tag -> {
                ImageTagDO imageTagDO = new ImageTagDO(saveImage.getId(), tag);
                imageTagDOS.add(imageTagDO);
            });
            imageTagDao.saveAll(imageTagDOS);
        }
        // 上传图片至七牛云
        QiniuUtils.uploadImage(request.getFile(), imageUrl);
        return new ImageCreateResponse(imageDO.getId(),imageDO.getUrl());
    }
}
