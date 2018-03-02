package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseEntity<Long> {

	private static final long serialVersionUID = -3802292814767103648L;

	private String name;

	private String description;
}
