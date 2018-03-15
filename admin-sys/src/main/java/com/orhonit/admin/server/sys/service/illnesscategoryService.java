package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Illnesscategory;

public interface illnesscategoryService {

	void save(Illnesscategory illnesscategory);

	Illnesscategory getById(Long id);

	void update(Illnesscategory illnesscategory);

	TableResponse<Illnesscategory> list(TableRequest request);

	void delete(Long id);

}
