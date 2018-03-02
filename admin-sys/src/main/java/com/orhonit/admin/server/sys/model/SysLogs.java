package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysLogs extends BaseEntity<Long> {

	private static final long serialVersionUID = -7809315432127036583L;
	private User user;
	private String module;
	private Boolean flag;
	private String remark;

}
