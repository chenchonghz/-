package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class District extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3354994096266801246L;
	
	private Integer upid;
	private String name;
	private Integer type;
	private Integer displayorder;
	private String spelling;

}
