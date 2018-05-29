package com.orhonit.admin.server.sys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Taskm;

public interface TaskmService {

	void save(Taskm taskm);

	Taskm getById(Long id);

	void update(Taskm taskm);

	TableResponse<Taskm> list(TableRequest request);

	void delete(Long id);

	List<Taskm> ten(long l);

	ResponseEntity<?> AppList();

	ResponseEntity<?> AppDelete(Long id);

	ResponseEntity<?> AppAddTaskm(Taskm taskm);

	ResponseEntity<?> getAll();


}
