package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medicalequipment extends BaseEntity<Long> {

	private String name;
	private String image;
	private String content;

}
