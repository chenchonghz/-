package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Slide extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4691895240996164974L;
	private String title;
	private String content;
	private String pic;
	private Integer status;

}
