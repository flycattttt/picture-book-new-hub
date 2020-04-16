package com.huiuoo.pc.db.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huiuoo.pc.common.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/15
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pb_gif_type")
public class GifTypeDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "type_name",nullable = false)
    private String typeName;

    @Basic
    @Column(name = "model_name",nullable = false)
    private String modelName;

    @Basic
    @Column(name = "delete",nullable = false)
    private Integer delete;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="gif_type_id")
    private List<GifDO> gifDOList;

    public GifTypeDO(String typeName,String modelName){
        this.typeName = typeName;
        this.modelName = modelName;
        this.delete = CommonStatus.VALID.getStatus();
    }

}
