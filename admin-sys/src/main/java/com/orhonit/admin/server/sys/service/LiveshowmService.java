package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Liveshowm;

public interface LiveshowmService {

	void save(Liveshowm liveshowm);

	Liveshowm getById(Long id);

	void update(Liveshowm liveshowm);

	TableResponse<Liveshowm> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> AddLiveShow(Liveshowm liveshowm);

	ResponseEntity<?> getLiveNow();

}
