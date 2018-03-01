package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.sys.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
