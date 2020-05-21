package com.huiuoo.pc.db.dataobject;

import javax.persistence.*;
import java.io.Serializable;

import com.huiuoo.pc.common.constant.CommonStatus;
import lombok.*;

/**
 * @Description  
 * @Author  lhf
 * @Date 2020-05-20 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="pb_cover" )
public class CoverDO implements Serializable {

	private static final long serialVersionUID =  2846437216953289345L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;

	/**
	 * 封面url
	 */
	@Column(name = "url" )
	private String url;

	/**
	 * 模板配置
	 */
	@Column(name = "template" )
	private String template;

	/**
	 * 模板配置
	 */
	@Column(name = "font_id" )
	private Long fontId;

	/**
	 * 1-使用  0-删除
	 */
	@Column(name = "deleted" )
	private Integer deleted;

	public CoverDO(String url, String template,Long fontId) {
		this.url = url;
		this.template = template;
		this.fontId = fontId;
		this.deleted = CommonStatus.VALID.getStatus();
	}
}
