package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.sys.dto.UserDto;
import com.orhonit.admin.server.sys.model.User;

public interface UserService {

	User saveUser(UserDto userDto);
	
	User updateUser(UserDto userDto);

	String passwordEncoder(String credentials, String salt);

	User getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
