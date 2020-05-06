package com.huiuoo.pc.web.controller;

import com.huiuoo.pc.common.advice.BaseController;
import com.huiuoo.pc.common.annotation.IgnoreJwtVerify;
import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.response.CommonReturnType;
import com.huiuoo.pc.db.dataobject.AdminDO;
import com.huiuoo.pc.db.service.IAdminService;
import com.huiuoo.pc.web.common.jwt.JwtToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@IgnoreJwtVerify
@RequestMapping("admin")
@RestController
public class AdminController extends BaseController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @IgnoreJwtVerify
    @PostMapping("getToken")
    public CommonReturnType getToken(String name, String password) throws BusinessException {
        AdminDO adminDO = adminService.adminLogin(name, password);
        String token = JwtToken.createToken(adminDO.getId());
        return CommonReturnType.create(token);
    }

    @IgnoreJwtVerify
    @GetMapping("get")
    public CommonReturnType get() {

        System.out.println(5/0);

        return CommonReturnType.create("get");
    }
}
