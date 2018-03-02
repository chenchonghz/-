package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.sys.dto.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
