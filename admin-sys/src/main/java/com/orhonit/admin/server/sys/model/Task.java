package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5215765815814116029L;
	private Integer herdsmanId;
	private Integer expertId;
	private String title;
	private String content;
	private Integer illnessCategoryId;
	private String processingMethod;
	private String enclosure;
	private Integer status;
	private Integer type;
	private Integer good;

	private String nameH;
	private String nameE;
	private String nameI;
}
