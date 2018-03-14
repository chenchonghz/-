package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Collections;

public interface CollectionsService {

	void save(Collections collections);

	Collections getById(Long id);

	void update(Collections collections);

	TableResponse<Collections> list(TableRequest request);

	void delete(Long id);

}
