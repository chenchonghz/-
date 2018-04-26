package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Prescriptionm;

public interface PrescriptionmService {

	void save(Prescriptionm prescriptionm);

	Prescriptionm getById(Long id);

	void update(Prescriptionm prescriptionm);

	TableResponse<Prescriptionm> list(TableRequest request);

	void delete(Long id);


}
