package com.orhonit.admin.server.sys.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionmDto{

	private Integer taskId;
	private Integer drugstoreId;
	private Integer drugId;
	private String drugName;
	private Integer drugNumber;
	private Integer status;
	private String pharmacyNameMeng;
	private String username;

}
