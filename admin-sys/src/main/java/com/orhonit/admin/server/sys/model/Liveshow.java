package com.orhonit.admin.server.sys.model;

import com.orhonit.admin.server.common.model.BaseEntity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Liveshow extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2228777452039996798L;
	private String coverPhoto;
	private Integer onlineApplyId;
	private Integer onlineQuantity;
	private String videoName;
	private String liveHome;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endTime;
	private int status;
	private String name;

}
