package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8111423200722424133L;
	private Integer herdsmanId;
	private String content;
	private Integer status;
	private Integer cateId;
	private Integer chlidrenId;
	private String name;
	private String title;

}
