package com.orhonit.admin.server.sys.service;

import java.util.List;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;

public interface DrugstoreinfoService {

	void save(Drugstoreinfo drugstoreinfo);

	Drugstoreinfo getById(Long id);

	Drugstoreinfo getByUid(int id);

	List<Drugstoreinfo> getByStatus(int id);

	int getDrugstoreUid(Long id);

	int getDrugstoreFailUid(Long id);

	void update(Drugstoreinfo drugstoreinfo);

	TableResponse<Drugstoreinfo> list(TableRequest request);

	void delete(Long id);

}
