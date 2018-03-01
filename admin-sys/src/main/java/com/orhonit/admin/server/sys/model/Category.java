package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category extends BaseEntity<Long> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2299952864691242729L;
	private Integer parentId;
	private String name;
	private String pName;

}
