package com.orhonit.admin.server.sys.service;

import java.util.List;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Slide;
import com.orhonit.admin.server.sys.model.Slidem;

public interface SlidemService {

	void save(Slidem slidem);

	Slidem getById(Long id);

	void update(Slidem slidem);

	TableResponse<Slidem> list(TableRequest request);

	void delete(Long id);

	List<Slide> getSlidemList();

}
