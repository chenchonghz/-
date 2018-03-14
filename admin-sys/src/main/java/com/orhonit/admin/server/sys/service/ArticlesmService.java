package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Articlesm;

public interface ArticlesmService {

	void save(Articlesm articlesm);

	Articlesm getById(Long id);

	void update(Articlesm articlesm);

	TableResponse<Articlesm> list(TableRequest request);

	void delete(Long id);

}
