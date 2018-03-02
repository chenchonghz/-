package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Education extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1196627189103306902L;
	private String name;

}
