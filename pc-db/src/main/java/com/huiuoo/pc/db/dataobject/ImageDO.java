package com.huiuoo.pc.db.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
public class ImageDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Basic
    @Column(name = "urls",nullable = false)
    private String urls;
    @Basic
    @Column(name = "size",nullable = false)
    private Long size;
    @Basic
    @Column(name = "create_time",nullable = false)
    private Date createTime;
    @Basic
    @Column(name = "deleted",nullable = false)
    private Integer deleted;
}
