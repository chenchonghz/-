package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drugstoreinfo extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5238325052880291728L;
	private Integer uid;
	private String pharmacyName;
	private String drugstoreLicense;
	private String drugstoreInformation;
	private Integer province;
	private Integer city;
	private Integer area;
	private String address;
	private String headerUrl;
	private Integer status;
	private String provinceName;
	private String cityName;
	private String areaName;

}
