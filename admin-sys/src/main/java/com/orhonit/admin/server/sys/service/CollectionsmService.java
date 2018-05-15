package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Collectionsm;

public interface CollectionsmService {

	void save(Collectionsm collectionsm);

	Collectionsm getById(Long id);

	void update(Collectionsm collectionsm);

	TableResponse<Collectionsm> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> appAdd(Integer cateId, Integer chlidrenId);

	ResponseEntity<?> Applook(Integer cateId);

	ResponseEntity<?> AppDelete(Long id);

}
