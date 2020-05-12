package com.huiuoo.pc.db.dataobject;

import javax.persistence.*;
import java.io.Serializable;

import com.huiuoo.pc.common.constant.CommonStatus;
import lombok.*;

import java.util.Date;

/**
 * @Description  
 * @Author  lhf
 * @Date 2020-05-11 
 */

@Data
@Entity
@Table ( name ="pb_book" )
@AllArgsConstructor
@NoArgsConstructor
public class BookDO implements Serializable {

	private static final long serialVersionUID =  7553201122925057196L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 小孩id
	 */
   	@Column(name = "child_id" )
	private Long childId;

	/**
	 * 绘本封面
	 */
   	@Column(name = "cover" )
	private String cover;

	/**
	 * 绘本内容
	 */
   	@Column(name = "content" )
	private String content;

	/**
	 * 昵称
	 */
   	@Column(name = "nick_name" )
	private String nickName;

	/**
	 * 标题
	 */
   	@Column(name = "title" )
	private String title;

	/**
	 * 评分
	 */
	@Column(name = "score" )
	private Integer score;

	/**
	 * 发布时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 更新时间
	 */
   	@Column(name = "update_time" )
	private Date updateTime;

	public BookDO(Long childId, String cover, String content, String nickName, String title) {
		this.childId = childId;
		this.cover = cover;
		this.content = content;
		this.nickName = nickName;
		this.title = title;
		this.score = 0;
		this.createTime = new Date();
		this.updateTime = this.createTime;
	}
}
