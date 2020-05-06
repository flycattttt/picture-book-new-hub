package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.constant.CommonStatus;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.redis.RedisUtil;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.common.utils.QiniuUtils;
import com.huiuoo.pc.db.dao.ImageDao;
import com.huiuoo.pc.db.dao.ImageTagDao;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.dataobject.ImageTagDO;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageGetRequest;
import com.huiuoo.pc.db.vo.ImageIndexResponse;
import com.huiuoo.pc.db.vo.ImageModelResponse;
import com.huiuoo.pc.db.vo.ImageUploadRequest;
import com.qiniu.common.QiniuException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @项目名称：picture-book-app-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/2
 * @version：V1.0
 */
@Service
public class ImageServiceImpl implements IImageService {

    private final RedisUtil redisUtil;

    private final ImageDao imageDao;

    private final ImageTagDao imageTagDao;

    public ImageServiceImpl(ImageDao imageDao, ImageTagDao imageTagDao, RedisUtil redisUtil) {
        this.imageDao = imageDao;
        this.imageTagDao = imageTagDao;
        this.redisUtil = redisUtil;
    }

    @Override
    public ImageIndexResponse getMaterialTypeByType(Integer type) {
        ImageIndexResponse response = new ImageIndexResponse();

        List<String> imageType = imageDao.findDistinctMaterialTypeByType(type);
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageable = PageRequest.of(0, 10, sort);
        List<ImageDO> imageDOList = imageDao.findByTypeAndMaterialType(type, imageType.get(0), pageable);
        response.setTypes(imageType);
        response.setImages(imageDOList);
        return response;
    }

    @Override
    public List<ImageDO> getImageList(ImageGetRequest request) {
        //排序条件
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        int page = request.getPage() == null ? 0 : request.getPage();
        int limit = request.getLimit() == null ? 10 : request.getLimit();
        Pageable pageable = PageRequest.of(page, limit, sort); // （当前页， 每页记录数， 排序方式）
        return imageDao.findByTypeAndMaterialType(request.getType(), request.getMaterialType(), pageable);
    }

    @Override
    public List<ImageModelResponse> getImageModelList(ImageGetRequest request) {
        //排序条件
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        int page = request.getPage() == null ? 0 : request.getPage();
        int limit = request.getLimit() == null ? 10 : request.getLimit();
        Pageable pageable = new PageRequest(page, limit, sort); // （当前页， 每页记录数， 排序方式）
        List<ImageDO> imageDOS = imageDao.findByTypeAndMaterialType(request.getType(), request.getMaterialType(), pageable);
        Map<String, List<ImageDO>> collect = imageDOS.stream().collect(Collectors.groupingBy(ImageDO::getModel));
        List<ImageModelResponse> responseList = new ArrayList<>();
        // 对模型图分组
        collect.forEach((a, b) -> {
            ImageModelResponse response = new ImageModelResponse();
            response.setModelName(a);
            response.setModelList(b);
            responseList.add(response);
        });
        // 使用stream流分页
        if (request.getPage() != null && request.getLimit() != null) {
            return responseList.stream()
                    .skip(pageable.getPageSize() * (pageable.getPageNumber() - 1))
                    .limit(pageable.getPageSize())
                    .collect(Collectors.toList());
        }
        return responseList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImageDO uploadImage(ImageUploadRequest request,Long userId) throws QiniuException, BusinessException {
        // 生成图片url名称
        String imageUrl = CommonUtils.generateImageUrl(
                Objects.requireNonNull(request.getFile().getOriginalFilename()), request.getType());

        ImageDO imageDO = new ImageDO();
        imageDO.setType(request.getType());
        imageDO.setMaterialType(request.getMaterialType());
        imageDO.setModel(request.getModel());
        imageDO.setUrl(imageUrl);
        imageDO.setDescription(StringUtils.isBlank(request.getDescription())?"":request.getDescription());
        imageDO.setSize(request.getFile().getSize());
        imageDO.setAdminId(userId);
        imageDO.setCreateTime(new Date());
        imageDO.setUpdateTime(imageDO.getCreateTime());
        imageDO.setDeleted(CommonStatus.VALID.getStatus());
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
        return imageDO;
    }
}
