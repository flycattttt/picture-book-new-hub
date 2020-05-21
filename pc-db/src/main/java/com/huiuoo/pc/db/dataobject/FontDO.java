package com.huiuoo.pc.db.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description  
 * @Author  lhf
 * @Date 2020-05-20 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="pb_font" )
public class FontDO implements Serializable {

	private static final long serialVersionUID =  2846437216953289345L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;

	/**
	 * url
	 */
   	@Column(name = "url" )
	private String url;

	/**
	 * 描述
	 */
   	@Column(name = "description" )
	private String description;

	/**
	 * 1-使用  0-删除
	 */
   	@Column(name = "deleted" )
	private Integer deleted;

}
