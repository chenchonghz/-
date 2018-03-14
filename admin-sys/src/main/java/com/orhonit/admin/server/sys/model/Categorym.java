package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categorym extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7044124899428534649L;
	private Integer parentId;
	private String name;

}
