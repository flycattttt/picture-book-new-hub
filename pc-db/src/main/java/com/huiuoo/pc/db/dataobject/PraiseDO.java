package com.huiuoo.pc.db.dataobject;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;

/**
 * @Description  
 * @Author  lhf
 * @Date 2020-05-12 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="pb_praise" )
public class PraiseDO implements Serializable {

	private static final long serialVersionUID =  886773063736230579L;

	/**
	 * 点赞id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;

	/**
	 * 绘本id
	 */
   	@Column(name = "book_id" )
	private Long bookId;

	/**
	 * 用户id
	 */
   	@Column(name = "user_id" )
	private Long userId;

	/**
	 * 1-赞 2-踩
	 */
   	@Column(name = "is_praise" )
	private Integer isPraise;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	public PraiseDO(Long bookId, Long userId, Integer isPraise) {
		this.bookId = bookId;
		this.userId = userId;
		this.isPraise = isPraise;
		this.createTime = new Date();
	}
}
