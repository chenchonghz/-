package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Newherdsman extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5876168094086228925L;
	private Integer uid;
	private Integer province;
	private Integer city;
	private Integer area;
	private String address;
	private String headerUrl;
	private Integer sex;
	private String cardNumber;
	private String name;
	private Integer provinceMeng;
	private Integer cityMeng;
	private Integer areaMeng;
	private String addressMeng;
	private String nameMeng;
	private String provinceName;
	private String cityName;
	private String areaName;
	private String provinceNamem;
	private String cityNamem;
	private String areaNamem;

}
