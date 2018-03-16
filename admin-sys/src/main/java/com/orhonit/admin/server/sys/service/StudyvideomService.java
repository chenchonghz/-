package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Studyvideom;

public interface StudyvideomService {

	void save(Studyvideom studyvideom);

	Studyvideom getById(Long id);

	void update(Studyvideom studyvideom);

	TableResponse<Studyvideom> list(TableRequest request);

	void delete(Long id);

}
