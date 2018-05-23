package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Introduce extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5757237178439708698L;
	private String title;
	private String introduce;

}
