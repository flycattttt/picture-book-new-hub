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
    @Column(name = "gender", nullable = false)
    private Integer gender;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "last_login_time", nullable = false)
    private Date lastLoginTime;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;
    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;
    @Basic
    @Column(name = "integral")
    private BigDecimal integral;
}
