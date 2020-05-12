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
@Table ( name ="pb_collect" )
public class CollectDO implements Serializable {

	private static final long serialVersionUID =  7238004306824708352L;

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
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	public CollectDO(Long bookId, Long userId) {
		this.bookId = bookId;
		this.userId = userId;
		this.createTime = new Date();
	}
}
