package com.orhonit.admin.server.sys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Drug;

public interface DrugService {

	void save(Drug drug);

	Drug getById(Long id);

	void update(Drug drug);

	TableResponse<Drug> list(TableRequest request);

	void delete(Long id);

	List<Drug> getByUid(Long uid);

	ResponseEntity<?> AppAdd(Drug drug);

	ResponseEntity<?> getByDid(Integer did);

	ResponseEntity<?> updateStatus(Integer status, Integer status2);

	ResponseEntity<?> updateNumber(Integer id, Integer number);


}
