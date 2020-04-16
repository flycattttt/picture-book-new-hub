package com.huiuoo.pc.db.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pb_image")
public class GifDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "gif_type_id",nullable = false)
    private Long gifTypeId;

    @Basic
    @Column(name = "url",nullable = false)
    private String url;

    @Basic
    @Column(name = "description",nullable = false)
    private String description;

    @Basic
    @Column(name = "size",nullable = false)
    private Long size;

    @Basic
    @Column(name = "admin_id",nullable = false)
    private Long adminId;

    @Basic
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Basic
    @Column(name = "update_time",nullable = false)
    private Date updateTime;

    @Basic
    @Column(name = "delete",nullable = false)
    private Integer delete;
}
