package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Prescription;

public interface PrescriptionService {

	void delete(Long id);

	TableResponse<Prescription> list(TableRequest request);

	void update(Prescription prescription);

	Prescription getById(Long id);

	void save(Prescription prescription);

	ResponseEntity<?> getP(Long taskId);

}
