package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyArticle extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845720612873580066L;
	private Integer uid;
	private String title;
	private String content;
	private String coverPhoto;
	private Integer clicks;
	private Integer status;
	private String enclosure;
	private String reason;
	private String name;
	private Integer categoryId;
	private String categoryName;
	
}
