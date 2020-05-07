package com.huiuoo.pc.db.service.impl;

import com.alibaba.fastjson.JSON;
import com.huiuoo.pc.common.constant.CommonStatus;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.db.dao.*;
import com.huiuoo.pc.db.dataobject.*;
import com.huiuoo.pc.db.service.IImageService;
import com.huiuoo.pc.db.vo.ImageUploadRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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

    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ImageCategoryDao imageCategoryDao;
    @Autowired
    private ImageTagDao imageTagDao;
    @Autowired
    private CategoryMaterialDao materialDao;
    @Autowired
    private CategoryDao categoryDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImageDO uploadImage(ImageUploadRequest request) throws BusinessException {

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
        for (int i = 0; i < size; i++) {
            // 生成图片url名称
            MultipartFile file = request.getFileList().get(i);
            String imageUrl = CommonUtils.generateImageUrl(
                    Objects.requireNonNull(file.getOriginalFilename()), categoryMaterialDO.getType());
            urls[i] = imageUrl;
            fileSize += file.getSize();
        }
        imageDO.setUrls(JSON.toJSONString(urls));
        imageDO.setSize(fileSize);
        imageDO.setCreateTime(new Date());
        imageDO.setDeleted(CommonStatus.VALID.getStatus());
        imageDao.save(imageDO);
        // 增加图片id对应的标签
        if (CollectionUtils.isNotEmpty(request.getTagList())) {
            // 去除重复标签
            List<String> uniqueTag = new ArrayList<>(new HashSet<>(request.getTagList()));
            List<ImageTagDO> imageTagDOS = new ArrayList<>();
            uniqueTag.forEach(tag -> {
                ImageTagDO imageTagDO = new ImageTagDO(imageDO.getId(), tag);
                imageTagDOS.add(imageTagDO);
            });
            imageTagDao.saveAll(imageTagDOS);
        }
        // 增加图片类别
        ImageCategoryDO imageCategoryDO = new ImageCategoryDO();
        imageCategoryDO.setImageId(imageDO.getId());
        imageCategoryDO.setCategoryId(request.getCategoryId());
        imageCategoryDO.setDescription(request.getDescription());
        imageCategoryDao.save(imageCategoryDO);
        return imageDO;
    }
}
