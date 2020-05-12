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
@Table(name = "pb_category")
public class CategoryDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Basic
    @Column(name = "pid",nullable = false)
    private Long pid;
    @Basic
    @Column(name = "hierarchy",nullable = false)
    private String hierarchy;
    @Basic
    @Column(name = "name",nullable = false)
    private String name;
}
