package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Liveshow;

public interface LiveshowService {

	void save(Liveshow liveshow);

	Liveshow getById(Long id);

	void update(Liveshow liveshow);

	TableResponse<Liveshow> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> AddLiveShow(Liveshow liveshow);

	ResponseEntity<?> getLiveNow();

	ResponseEntity<?> addPeople(Integer id);

	ResponseEntity<?> lessPeople(Integer id);

}
