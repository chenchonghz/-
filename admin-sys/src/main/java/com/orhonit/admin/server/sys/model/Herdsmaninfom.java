package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Herdsmaninfom extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7789248280648224039L;
	private Integer uid;
	private Integer province;
	private Integer city;
	private Integer area;
	private String address;
	private String headerUrl;
	private Integer sex;
	private String cardNumber;
	private String name;

}
