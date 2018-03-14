package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drug extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2891358631289297129L;
	private Integer categoryId;
	private String title;
	private String titlemeng;
	private String content;
	private String contentmeng;
	private String drugUrl;
	private Integer uid;
	private Integer number;
	private Integer status;

}
