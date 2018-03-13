package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Medicalequipment;

public interface MedicalequipmentSerivce {

	void save(Medicalequipment medicalequipment);

	Medicalequipment getById(Long id);

	void update(Medicalequipment medicalequipment);

	TableResponse<Medicalequipment> list(TableRequest request);

	void delete(Long id);

}
