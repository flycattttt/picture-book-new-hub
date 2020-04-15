package com.huiuoo.pc.db.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Table(name = "pb_image_type")
public class ImageTypeDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "main_type",nullable = false)
    private Integer mainType;

    @Basic
    @Column(name = "follow_type",nullable = false)
    private String followType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="image_type_id")
    private List<ImageDO> imageDOS;

    public ImageTypeDO(Integer mainType,String followType){
        this.mainType = mainType;
        this.followType = followType;
    }

}
