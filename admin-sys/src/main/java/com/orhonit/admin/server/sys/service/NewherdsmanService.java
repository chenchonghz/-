package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Newherdsman;

public interface NewherdsmanService {

	void save(Newherdsman newherdsman);

	Newherdsman getById(Long id);

	void update(Newherdsman newherdsman);

	TableResponse<Newherdsman> list(TableRequest request);

	void delete(Long id);

	Newherdsman getByUid(int id);

	Newherdsman getByUidm(int id);
	
	Newherdsman getUid(Long id);

}
