package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Medicalequipmentm;

public interface MedicalequipmentmService {

	void save(Medicalequipmentm medicalequipmentm);

	Medicalequipmentm getById(Long id);

	void update(Medicalequipmentm medicalequipmentm);

	TableResponse<Medicalequipmentm> list(TableRequest request);

	void delete(Long id);

}
