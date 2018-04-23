package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Educationm;

public interface EducationmService {

	void save(Educationm educationm);

	Educationm getById(Long id);

	void update(Educationm educationm);

	TableResponse<Educationm> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> getList();

}
