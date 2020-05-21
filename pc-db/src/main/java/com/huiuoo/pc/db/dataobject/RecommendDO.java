package com.huiuoo.pc.db.dataobject;

import javax.persistence.*;
import java.io.Serializable;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;

/**
 * @Description  
 * @Author  lhf
 * @Date 2020-05-18 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="pb_recommend" )
public class RecommendDO implements Serializable {

	private static final long serialVersionUID =  5960959797140187103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * 图片id
	 */
   	@Column(name = "image_id" )
	private Long imageId;

	/**
	 * 推荐图片id
	 */
   	@Column(name = "recom_id" )
	private Long recomId;

	/**
	 * 相似度
	 */
   	@Column(name = "similarity" )
	private Integer similarity;

}
