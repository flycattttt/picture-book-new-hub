package com.huiuoo.pc.db.dataobject;


import com.huiuoo.pc.common.constant.CommonStatus;
import com.huiuoo.pc.common.constant.SexType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @项目名称：picture-book-app-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/2
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pb_user")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;
    @Basic
    @Column(name = "login_type", nullable = false)
    private String loginType;
    @Basic
    @Column(name = "open_id")
    private String openId;
    @Basic
    @Column(name = "integral")
    private BigDecimal integral;
    @Basic
    @Column(name = "sex", nullable = false)
    private Integer sex;
    @Basic
    @Column(name = "head_img")
    private String headImg;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;
    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
    @Basic
    @Column(name = "last_login_time", nullable = false)
    private Date lastLoginTime;
    @Basic
    @Column(name = "deleted", nullable = false)
    private Integer deleted;

    // 手机号码注册对象

    public UserDO(String name, String phone, String loginType) {
        this.name = name;
        this.phone = phone;
        this.loginType = loginType;
        this.openId = "";
        this.integral = new BigDecimal(0);
        this.sex = SexType.OTHER.getType();
        this.headImg = "";
        this.createTime = new Date();
        this.updateTime = this.createTime;
        this.lastLoginTime = this.createTime;
        this.deleted = CommonStatus.VALID.getStatus();
    }

    // 第三方登录注册对象
    public UserDO(String name, String loginType,
                  String openId, String headImg) {
        this.name = name;
        this.phone = "";
        this.loginType = loginType;
        this.openId = openId;
        this.integral = new BigDecimal(0);
        this.sex = SexType.OTHER.getType();
        this.headImg = headImg;
        this.createTime = new Date();
        this.updateTime = this.createTime;
        this.lastLoginTime = this.createTime;
        this.deleted = CommonStatus.VALID.getStatus();
    }
}
