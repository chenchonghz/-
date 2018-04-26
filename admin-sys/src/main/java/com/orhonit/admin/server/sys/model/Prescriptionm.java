package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prescriptionm extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4115233693017219906L;
	private Integer taskId;
	private Integer drugstoreId;
	private Integer drugId;
	private String drugName;
	private Integer drugNumber;
	private Integer status;

}
