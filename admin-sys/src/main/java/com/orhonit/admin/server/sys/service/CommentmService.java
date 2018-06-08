package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Commentm;

public interface CommentmService {

	void save(Commentm commentm);

	Commentm getById(Long id);

	void update(Commentm commentm);

	TableResponse<Commentm> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> saveCom(Commentm commentm);

	ResponseEntity<?> examine(Integer id);

	ResponseEntity<?> getList();

	ResponseEntity<?> getCBycid(Integer cateId, Integer childrenId);

	int commentmFail(Long id);

	int commentmPass(Long id);

}
