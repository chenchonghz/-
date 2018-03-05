package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medicalequipmentm extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1078652789716436466L;
	private String name;
	private String image;
	private String content;

}
