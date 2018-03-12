package com.orhonit.admin.server.sys.service;

import java.util.List;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Category;

public interface CategoryService {

	void save(Category category);

	Category getById(Long id);

	void update(Category category);

	List<Category> listAll();

	TableResponse<Category> list(TableRequest request);

	void delete(Long id);
}
