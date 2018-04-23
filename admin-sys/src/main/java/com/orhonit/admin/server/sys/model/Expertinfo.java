package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Expertinfo extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 973782507706244058L;
	private Integer uid;
	private String expertCertificate;
	private Integer education;
	private Integer educationMeng;
	private String educationName;
	private String educationMengName;
	private String personal;
	private String personalMeng;
	private Integer status;
	private Integer state;
	private Integer dealingProblems;
	private String expertise;
	private String expertiseMeng;
	private Integer activity;
	private Integer videoDiagnosis;
	private Integer phoneDiagnosis;
	private String address;
	private String addressMeng;
	private Integer sex;
	private String headerUrl;
	private String cardNumber;
	private String name;
	private String nameMeng;
	private String reason;

}
