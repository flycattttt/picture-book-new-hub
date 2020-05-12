package com.huiuoo.pc.db.dataobject;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  lhf
 * @Date 2020-05-11 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="pb_type" )
public class TypeDO implements Serializable {

	private static final long serialVersionUID =  1221824247376344573L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 绘本分类名称
	 */
   	@Column(name = "type" )
	private String type;

}
