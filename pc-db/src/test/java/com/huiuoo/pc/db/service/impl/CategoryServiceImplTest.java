package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.utils.QiniuUtils;
import com.huiuoo.pc.db.service.ICategoryService;
import com.huiuoo.pc.db.service.IImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IImageService imageService;

    @Test
    public void test() {

    }

}