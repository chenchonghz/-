package com.orhonit.admin.server.sys.service;


import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Articles;

public interface ArticlesService {

	void save(Articles articles);

	Articles getById(Long id);

	void update(Articles articles);

	void delete(Long id);

	TableResponse<Articles> listArticles(TableRequest request);
	
}
