package com.orhonit.admin.server.sys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Districtm;

public interface DistrictmService {

	void save(Districtm districtm);

	List<Districtm> getByUpid(int i);

	Districtm getById(Long id);

	void update(Districtm districtm);

	TableResponse<Districtm> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> getAll();


}
