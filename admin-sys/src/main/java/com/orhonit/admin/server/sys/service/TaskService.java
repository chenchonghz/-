package com.orhonit.admin.server.sys.service;

import java.util.List;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Task;

public interface TaskService {

	void save(Task task);

	Task getById(Long id);

	void update(Task task);

	TableResponse<Task> list(TableRequest request);

	void delete(Long id);

	List<Task> ten(long start);

}
