package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Taskm extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6126312449297460462L;
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
	@Override
	public String toString() {
		return "Taskm [herdsmanId=" + herdsmanId + ", expertId=" + expertId + ", title=" + title + ", content="
				+ content + ", illnessCategoryId=" + illnessCategoryId + ", processingMethod=" + processingMethod
				+ ", enclosure=" + enclosure + ", status=" + status + ", type=" + type + ", good=" + good + "]";
	}

	
}
