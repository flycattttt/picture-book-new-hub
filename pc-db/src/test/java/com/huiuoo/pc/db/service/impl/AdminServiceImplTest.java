package com.huiuoo.pc.db.service.impl;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.utils.CommonUtils;
import com.huiuoo.pc.db.service.IAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/6
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceImplTest {

    @Autowired
    private IAdminService adminService;

    @Test
    public void testMd5() throws BusinessException {

    }


}