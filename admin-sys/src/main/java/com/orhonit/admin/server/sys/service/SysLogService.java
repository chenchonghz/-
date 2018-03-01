package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.sys.model.SysLogs;

/**
 * 日志service
 * 
 * @author caodw
 *
 *
 */
public interface SysLogService {

	void save(SysLogs sysLogs);

	void save(Long userId, String module, Boolean flag, String remark);
}
