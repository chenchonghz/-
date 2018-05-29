package com.orhonit.admin.server.sys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Studyvideom;

public interface StudyvideomService {

	void save(Studyvideom studyvideom);

	Studyvideom getById(Long id);

	void update(Studyvideom studyvideom);

	TableResponse<Studyvideom> list(TableRequest request);

	void delete(Long id);

	Studyvideom frist();

	List<Studyvideom> ten(long l);

	ResponseEntity<?> getAll();

	ResponseEntity<?> addStudyVideom(Integer id);

}
