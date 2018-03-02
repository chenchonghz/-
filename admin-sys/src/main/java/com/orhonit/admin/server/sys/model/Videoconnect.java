package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Videoconnect extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9017752334714643837L;
	private Integer hid;
	private Integer eid;
	private Integer roomid;
	private String url;

}
