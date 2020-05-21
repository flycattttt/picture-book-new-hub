package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.db.dao.ImageDao;
import com.huiuoo.pc.db.dao.RecommendDao;
import com.huiuoo.pc.db.dataobject.ImageDO;
import com.huiuoo.pc.db.dataobject.RecommendDO;
import com.huiuoo.pc.db.service.IRecommendService;
import com.huiuoo.pc.db.vo.RecommAddRequest;
import com.huiuoo.pc.db.vo.RecommGetResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/18
 */
@Service
public class RecommendServiceImpl implements IRecommendService {

    @Autowired
    private RecommendDao recommendDao;

    @Autowired
    private ImageDao imageDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RecommendDO add(RecommAddRequest request) throws BusinessException {

        if (request.getImageId().equals(request.getRecomId())){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR,"imageId和recomId不能相同");
        }
        Optional<ImageDO> image = imageDao.findById(request.getImageId());
        Optional<ImageDO> recommend = imageDao.findById(request.getRecomId());
        if (!image.isPresent() || !recommend.isPresent()){
            throw new BusinessException(EmBusinessError.CAN_NOT_FIND_RECORD);
        }
        RecommendDO recommendDO = new RecommendDO();
        recommendDO.setImageId(request.getImageId());
        recommendDO.setRecomId(request.getRecomId());
        recommendDO.setSimilarity(request.getSimilarity());
        return recommendDao.save(recommendDO);
    }

    /**
     * @Description:
     * @param: number指最终获取的组数
     * @return:
     */
    @Override
    public List<RecommGetResponse> recommendImage(Integer number) throws BusinessException {
        if (number == null || number<=0){
            return Collections.emptyList();
        }
        int RECOM_COUNT = 3; // 推荐图RECOM_COUNT = 最终每组显示的数量 - 1;
        List<Long> imageIds = recommendDao.findGroupByImageId(RECOM_COUNT); // 查出对应recomId数量大于RECOM_COUNT的imageId
        if (CollectionUtils.isEmpty(imageIds)){
            return Collections.emptyList();
        }
        List<RecommGetResponse> responseList = new ArrayList<>();
        Sort sort = new Sort(Sort.Direction.DESC, "similarity");//倒序
        Pageable pageable = PageRequest.of(0, RECOM_COUNT, sort); // 限制每组获取的相似图片数量进行
        for (int i = 0; i <number ; i++) {
            RecommGetResponse response = new RecommGetResponse();
            int random = CommonUtils.getRandom(imageIds.size()); // 以随机数为下标,每次循环随机获取一组
            Long imageId = imageIds.get(random);
            // 拿到每组的recomId,查找图片信息
            List<Long> recomIds = recommendDao.findAllByImageId(imageId,pageable).stream().map(RecommendDO::getRecomId).collect(Collectors.toList());
            recomIds.add(imageId);// 1个imageId + RECOM_COUNT个recomId;
            response.setImageList(imageDao.findAllByIdIn(recomIds,null));
            responseList.add(response);
        }
        return responseList;
    }
}
