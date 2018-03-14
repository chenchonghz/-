package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Articlesm extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1117643225784797996L;
	private String title;
	private String content;
	private Integer status;

}
