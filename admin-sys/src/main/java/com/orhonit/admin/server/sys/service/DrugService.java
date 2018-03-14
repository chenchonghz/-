package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Drug;

public interface DrugService {

	void save(Drug drug);

	Drug getById(Long id);

	void update(Drug drug);

	TableResponse<Drug> list(TableRequest request);

	void delete(Long id);

}
