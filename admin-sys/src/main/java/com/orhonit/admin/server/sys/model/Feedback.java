package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feedback extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5975426330768470227L;
	private Integer uid;
	private String opinion;

}
