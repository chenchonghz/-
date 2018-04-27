package com.orhonit.admin.server.sys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskmDto {
	private Integer herdsmanId;
	private String husername;
	private Integer expertId;
	private String eusername;
	private String title;
	private String content;
	private Integer illnessCategoryId;
	private String processingMethod;
	private String enclosure;
	private Integer status;
	private Integer type;
	private Integer good;
}
