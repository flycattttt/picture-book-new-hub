package com.huiuoo.pc.db.dataobject;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @Description  
 * @Author  lhf
 * @Date 2020-05-12 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="pb_ad" )
public class AdDO implements Serializable {

	private static final long serialVersionUID =  5447301276296512600L;

	/**
	 * 广告id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * 广告标题
	 */
   	@Column(name = "title" )
	private String title;

	/**
	 * 所广告的商品页面或者活动页面链接地址
	 */
   	@Column(name = "link" )
	private String link;

	/**
	 * 广告宣传图片
	 */
   	@Column(name = "img_url" )
	private String imgUrl;

	/**
	 * 广告位置：1则是首页
	 */
   	@Column(name = "position" )
	private String position;

	/**
	 * 活动内容
	 */
   	@Column(name = "content" )
	private String content;

	/**
	 * 广告开始时间
	 */
   	@Column(name = "start_time" )
	private Date startTime;

	/**
	 * 广告结束时间
	 */
   	@Column(name = "end_time" )
	private Date endTime;

	/**
	 * 是否启动
	 */
   	@Column(name = "enabled" )
	private Integer enabled;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 更新时间
	 */
   	@Column(name = "update_time" )
	private Date updateTime;

	/**
	 * 逻辑删除
	 */
   	@Column(name = "deleted" )
	private Integer deleted;

}
