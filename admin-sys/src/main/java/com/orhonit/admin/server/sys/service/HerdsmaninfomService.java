package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Herdsmaninfom;

public interface HerdsmaninfomService {

	void save(Herdsmaninfom herdsmaninfom);

	Herdsmaninfom getById(Long id);

	void update(Herdsmaninfom herdsmaninfom);

	TableResponse<Herdsmaninfom> list(TableRequest request);

	void delete(Long id);

}
