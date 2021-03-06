package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Articles extends BaseEntity<Long> {

	private static final long serialVersionUID = -4401913568806243090L;

	private String title;
	private String titleMeng;
	private String contentMeng;
	private String content;
	private Integer status;

	public interface Status {
		int DRAFT = 0;
		int PUBLISH = 1;
	}

}
