package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyVideo extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5879190014109839667L;
	private Integer uid;
	private Integer categoryId;
	private String videoUrl;
	private String title;
	private String content;
	private String coverPhoto;
	private Integer clicks;
	private Integer status;
	private String reason;
	private String name;
	private String categoryName;

}
