package com.orhonit.admin.server.online.dto;

import com.orhonit.admin.server.sys.model.Videoconnect;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息变动通知对象
 * 
 * @author caodw
 *
 */
@Getter
@Setter
public class VideoDto extends BaseDto {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7116561077314172843L;
	private Videoconnect videoconnect;
}
