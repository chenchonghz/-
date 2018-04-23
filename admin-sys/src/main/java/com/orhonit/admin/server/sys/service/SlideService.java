package com.orhonit.admin.server.sys.service;

import java.util.List;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Slide;

public interface SlideService {

	void save(Slide slide);

	Slide getById(Long id);

	void update(Slide slide);

	TableResponse<Slide> list(TableRequest request);

	void delete(Long id);

	List<Slide> getSlideList();

}
