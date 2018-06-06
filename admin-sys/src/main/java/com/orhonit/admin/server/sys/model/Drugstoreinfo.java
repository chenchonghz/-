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
	private String pharmacyNameMeng;
	private String drugstoreLicense;
	private String drugstoreInformation;
	private String drugstoreInformationMeng;
	private Integer province;
	private Integer provinceMeng;
	private Integer city;
	private Integer cityMeng;
	private Integer area;
	private Integer areaMeng;
	private String address;
	private String addressMeng;
	private String headerUrl;
	private Integer status;
	private String provinceName;
	private String cityName;
	private String areaName;
	private String provinceNameMeng;
	private String cityNameMeng;
	private String areaNameMeng;
	private String reason;
	@Override
	public String toString() {
		return "Drugstoreinfo [uid=" + uid + ", pharmacyName=" + pharmacyName + ", pharmacyNameMeng=" + pharmacyNameMeng
				+ ", drugstoreLicense=" + drugstoreLicense + ", drugstoreInformation=" + drugstoreInformation
				+ ", drugstoreInformationMeng=" + drugstoreInformationMeng + ", province=" + province
				+ ", provinceMeng=" + provinceMeng + ", city=" + city + ", cityMeng=" + cityMeng + ", area=" + area
				+ ", areaMeng=" + areaMeng + ", address=" + address + ", addressMeng=" + addressMeng + ", headerUrl="
				+ headerUrl + ", status=" + status + ", provinceName=" + provinceName + ", cityName=" + cityName
				+ ", areaName=" + areaName + ", provinceNameMeng=" + provinceNameMeng + ", cityNameMeng=" + cityNameMeng
				+ ", areaNameMeng=" + areaNameMeng + "]";
	}
	
	

}
