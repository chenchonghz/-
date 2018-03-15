package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Illnesscategory extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1451268403894813364L;
	private Integer parentId;
	private String name;

}
