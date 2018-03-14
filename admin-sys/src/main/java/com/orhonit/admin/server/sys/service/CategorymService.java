package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Categorym;

public interface CategorymService {

	void save(Categorym categorym);

	Categorym getById(Long id);

	void update(Categorym categorym);

	TableResponse<Categorym> list(TableRequest request);

	void delete(Long id);

}
