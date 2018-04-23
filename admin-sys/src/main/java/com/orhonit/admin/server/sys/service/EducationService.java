package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Education;

public interface EducationService {

	void save(Education education);

	Education getById(Long id);

	void update(Education education);

	TableResponse<Education> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> getList();

}
