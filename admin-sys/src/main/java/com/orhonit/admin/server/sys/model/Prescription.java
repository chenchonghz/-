package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prescription extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -771700379570476330L;
	private Integer taskId;
	private Integer drugstoreId;
	private Integer drugId;
	private String drugName;
	private Integer drugNumber;
	private Integer status;
	@Override
	public String toString() {
		return "Prescription [taskId=" + taskId + ", drugstoreId=" + drugstoreId + ", drugId=" + drugId + ", drugName="
				+ drugName + ", drugNumber=" + drugNumber + ", status=" + status + "]";
	}
	

}
