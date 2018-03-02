package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Districtm extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4776035051574419201L;
	
	private Integer upid;
	private String name;
	private Integer type;
	private Integer displayorder;
	private String spelling;

}
