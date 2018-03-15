package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Illnesscategorym extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -458772646728508574L;
	private Integer parentId;
	private String name;

}
