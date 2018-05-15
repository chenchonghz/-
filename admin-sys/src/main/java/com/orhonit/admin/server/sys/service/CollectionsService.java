package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Collections;

public interface CollectionsService {

	void save(Collections collections);

	Collections getById(Long id);

	void update(Collections collections);

	TableResponse<Collections> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> appAdd(Integer cateId, Integer chlidrenId);

	ResponseEntity<?> Applook(Integer cateId);

	ResponseEntity<?> AppDelete(Long id);

}
