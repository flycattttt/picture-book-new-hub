package com.huiuoo.pc.db.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pb_child")
public class ChildDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "gender", nullable = false)
    private Integer gender;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "integral")
    private BigDecimal integral;
    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;
    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
    @Basic
    @Column(name = "last_login_time", nullable = false)
    private Date lastLoginTime;
}
