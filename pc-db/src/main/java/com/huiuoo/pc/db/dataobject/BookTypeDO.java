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
@Table ( name ="pb_book_type" )
public class BookTypeDO implements Serializable {

	private static final long serialVersionUID =  7390597540939460812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 绘本id
	 */
   	@Column(name = "book_id" )
	private Long bookId;

	/**
	 * 绘本分类id
	 */
   	@Column(name = "type_id" )
	private Long typeId;

}
