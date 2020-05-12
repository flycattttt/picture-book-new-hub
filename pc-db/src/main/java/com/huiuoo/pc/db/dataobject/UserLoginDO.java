package com.huiuoo.pc.db.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pb_user_login")
public class UserLoginDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Basic
    @Column(name = "identity", nullable = false)
    private String identity;
    @Basic
    @Column(name = "login_type", nullable = false)
    private Integer loginType;
}
