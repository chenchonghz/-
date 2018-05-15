package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Collectionsm extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3129416914094008417L;
	private Integer herdsmanId;
	private Integer cateId;
	private Integer chlidrenId;

}
