package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.common.utils.QiniuUtils;
import com.huiuoo.pc.db.dao.CoverDao;
import com.huiuoo.pc.db.dao.FontDao;
import com.huiuoo.pc.db.dataobject.CoverDO;
import com.huiuoo.pc.db.dataobject.FontDO;
import com.huiuoo.pc.db.service.ICoverService;
import com.huiuoo.pc.db.vo.CoverAddRequest;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @类创建人：lhf
 * @类创建时间：2020/5/20
 */
@Service
public class CoverServiceImpl implements ICoverService {

    @Autowired
    private CoverDao coverDao;

    @Autowired
    private FontDao fontDao;

    @Override
    public List<CoverDO> findAll() {
        return coverDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CoverDO add(CoverAddRequest request) throws BusinessException, QiniuException {
        if (request.getImgFile()==null || request.getImgFile().getSize()==0){
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR);
        }
        Optional<FontDO> fontDO = fontDao.findById(request.getFontId());
        if (!fontDO.isPresent()){
            throw new BusinessException(EmBusinessError.CAN_NOT_FIND_RECORD,"fontId不存在");
        }
        MultipartFile imgFile = request.getImgFile();
        String imageUrl = CommonUtils.generateImageUrl(Objects.requireNonNull(imgFile.getOriginalFilename()), 4);
        CoverDO coverDO = coverDao.save(new CoverDO(
                imageUrl,
                request.getTemplate(),
                request.getFontId()
        ));
        QiniuUtils.uploadImage(imgFile,imageUrl);
        return coverDO;
    }


}
