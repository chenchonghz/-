package com.orhonit.admin.server.sys.service;

import java.util.List;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.District;

public interface DistrictService {

	void save(District district);

	List<District> getByUpid(int i);

	void delete(Long id);

	District getById(Long id);

	void update(District district);

	TableResponse<District> list(TableRequest request);

}
