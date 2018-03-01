package com.orhonit.admin.server.online.dto;

import com.orhonit.admin.server.sys.model.User;

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
public class LoginUserDto extends BaseDto {

	private static final long serialVersionUID = 3010210138476209428L;

	private User user;
}
