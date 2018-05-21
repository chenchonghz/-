package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Liveshowm extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4687772857716118598L;
	private String coverPhoto;
	private Integer onlineApplyId;
	private Integer onlineQuantity;
	private String videoName;
	private String liveHome;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endTime;

}
