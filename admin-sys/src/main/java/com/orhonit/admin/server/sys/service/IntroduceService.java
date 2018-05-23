package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Introduce;

public interface IntroduceService {

	void save(Introduce introduce);

	Introduce getById(Long id);

	void update(Introduce introduce);

	TableResponse<Introduce> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> getAll();

}
