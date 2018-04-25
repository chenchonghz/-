package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Illnesscategorym;

public interface IllnesscategorymService {

	void save(Illnesscategorym illnesscategorym);

	Illnesscategorym getById(Long id);

	void update(Illnesscategorym illnesscategorym);

	TableResponse<Illnesscategorym> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> AppList();

}
