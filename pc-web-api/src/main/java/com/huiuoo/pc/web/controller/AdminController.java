package com.huiuoo.pc.web.controller;

import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.db.dataobject.AdminDO;
import com.huiuoo.pc.db.service.IAdminService;
import com.huiuoo.pc.web.common.jwt.JwtToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @项目名称：picture-book-new
 * @类描述：管理员服务
 * @类创建人：lhf
 * @类创建时间：2020/5/11
 */
@IgnoreJwtVerify
@RequestMapping("admin")
@RestController
public class AdminController  {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @IgnoreJwtVerify
    @PostMapping("getToken")
    public String getToken(String name, String password) throws BusinessException {
        AdminDO adminDO = adminService.adminLogin(name, password);
        return JwtToken.createToken(adminDO.getId());
    }
}
