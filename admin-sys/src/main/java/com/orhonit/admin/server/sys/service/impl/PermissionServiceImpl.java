package com.orhonit.admin.server.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orhonit.admin.server.sys.dao.PermissionDao;
import com.orhonit.admin.server.sys.model.Permission;
import com.orhonit.admin.server.sys.service.PermissionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "adminLogger")
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public void save(Permission permission) {
		permissionDao.save(permission);

		log.debug("新增菜单{}", permission.getName());
	}

	@Override
	public void update(Permission permission) {
		permissionDao.update(permission);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		permissionDao.deleteRolePermission(id);
		permissionDao.delete(id);
		permissionDao.deleteByParentId(id);

		log.debug("删除菜单id:{}", id);
	}

}
