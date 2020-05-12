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
@Table(name = "pb_category_material")
public class CategoryMaterialDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Basic
    @Column(name = "type",nullable = false)
    private Integer type;
    @Basic
    @Column(name = "category_id",nullable = false)
    private Long categoryId;
}
