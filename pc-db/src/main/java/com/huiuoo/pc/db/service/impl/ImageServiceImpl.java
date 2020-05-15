package com.huiuoo.pc.db.service.impl;

import com.alibaba.fastjson.JSON;
import com.huiuoo.pc.common.constant.CommonStatus;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.common.utils.QiniuUtils;
import com.huiuoo.pc.db.dao.*;
import com.huiuoo.pc.db.dataobject.*;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageGetRequest;
import com.huiuoo.pc.db.vo.ImageTagGetRequest;
import com.huiuoo.pc.db.vo.ImageUploadRequest;
import com.qiniu.common.QiniuException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ImageCategoryDao imageCategoryDao;
    @Autowired
    private ImageTagDao imageTagDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private CategoryMaterialDao materialDao;
    @Autowired
    private CategoryDao categoryDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImageDO uploadImage(ImageUploadRequest request) throws BusinessException, QiniuException {

        // 判断categoryId是否存在
        Optional<CategoryDO> categoryDO = categoryDao.findById(request.getCategoryId());
        if (!categoryDO.isPresent()) {
            throw new BusinessException(EmBusinessError.CAN_NOT_FIND_RECORD);
        }
        CategoryMaterialDO categoryMaterialDO = materialDao.findByCategoryId(request.getCategoryId());
        if (null == categoryMaterialDO) {
            throw new BusinessException(EmBusinessError.CAN_NOT_FIND_RECORD);
        }
        int size = request.getFileList().size();
        if (size == 0) {
            return null;
        }
        // 增加图片数据
        ImageDO imageDO = new ImageDO();
        String[] urls = new String[size];
        long fileSize = 0L;
        List<MultipartFile> fileList = request.getFileList();
        for (int i = 0; i < size; i++) {
            MultipartFile file = fileList.get(i);
            // 生成图片url名称
            String imageUrl = CommonUtils.generateImageUrl(Objects.requireNonNull(file.getOriginalFilename()), categoryMaterialDO.getType());
            urls[i] = imageUrl;
            fileSize += file.getSize();
            QiniuUtils.uploadImage(file, imageUrl);
        }
        imageDO.setUrls(JSON.toJSONString(urls));
        imageDO.setSize(fileSize);
        imageDO.setCreateTime(new Date());
        imageDO.setDeleted(CommonStatus.VALID.getStatus());
        imageDao.save(imageDO);
        // 增加图片对应的标签
        if (CollectionUtils.isNotEmpty(request.getTagList())) {
            // 去除重复标签
            List<String> uniqueTag = new ArrayList<>(new HashSet<>(request.getTagList()));
            uniqueTag.forEach(tag -> {
                ImageTagDO imageTagDO = new ImageTagDO();
                TagDO tagDO = tagDao.findByTag(tag);
                if (tagDO == null) {
                    tagDO = new TagDO();
                    tagDO.setTag(tag);
                    tagDao.save(tagDO);
                }
                imageTagDO.setImageId(imageDO.getId());
                imageTagDO.setTagId(tagDO.getId());
                imageTagDao.save(imageTagDO);
            });
        }
        // 增加图片-类别
        ImageCategoryDO imageCategoryDO = new ImageCategoryDO();
        imageCategoryDO.setImageId(imageDO.getId());
        imageCategoryDO.setCategoryId(request.getCategoryId());
        imageCategoryDO.setDescription(request.getDescription());
        imageCategoryDao.save(imageCategoryDO);
        return imageDO;
    }

    @Override
    public List<ImageDO> findPageByCategoryId(ImageGetRequest request) {
        List<ImageCategoryDO> categoryDOS = imageCategoryDao.findAllByCategoryId(request.getCategoryId());
        if (CollectionUtils.isEmpty(categoryDOS)) {
            return Collections.emptyList();
        }
        List<Long> list = categoryDOS.stream().map(ImageCategoryDO::getImageId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        //排序条件
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit(), sort);
        return imageDao.findAllByIdIn(list, pageable);
    }

    @Override
    public List<ImageDO> findPageByTags(ImageTagGetRequest request) {

        List<String> tagList = request.getTags();

        if (CollectionUtils.isEmpty(tagList)) {
            return Collections.emptyList();
        }
        List<Long> tagIds = tagDao.findAllByTagIn(tagList).stream().map(TagDO::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(tagIds)) {
            return Collections.emptyList();
        }
        List<Long> imageIds = imageTagDao.findAllByTagIdIn(tagIds).stream().map(ImageTagDO::getImageId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(imageIds)) {
            return Collections.emptyList();
        }
        //排序条件
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit(), sort);
        return new ArrayList<>(imageDao.findAllByIdIn(imageIds, pageable));
    }
}
