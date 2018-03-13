package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Herdsmaninfo;

public interface HerdsmaninfoService {

	void delete(Long id);

	TableResponse<Herdsmaninfo> list(TableRequest request);

	void update(Herdsmaninfo herdsmaninfo);

	Herdsmaninfo getByUid(int id);

	Herdsmaninfo getById(Long id);

	void save(Herdsmaninfo herdsmaninfo);

}
