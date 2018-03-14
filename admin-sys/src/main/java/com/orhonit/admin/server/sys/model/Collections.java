package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Collections extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5145111791014009400L;
	private Integer herdsmanId;
	private Integer cateId;
	private Integer chlidrenId;

}
