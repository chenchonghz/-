package com.orhonit.admin.server.sys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;

public interface DrugstoreinfoService {

	void save(Drugstoreinfo drugstoreinfo);

	Drugstoreinfo getById(Long id);

	Drugstoreinfo getByUid(int id);

	List<Drugstoreinfo> getByStatus(int id);

	int getDrugstoreUid(Long id);

	int getDrugstoreFailUid(Long id, String pass);

	void update(Drugstoreinfo drugstoreinfo);

	TableResponse<Drugstoreinfo> list(TableRequest request);

	void delete(Long id);

	List<Drugstoreinfo> ten(Long start);

	ResponseEntity<?> AppGet(int parseInt);

	ResponseEntity<?> AppSave(Drugstoreinfo drugstoreinfo);

	ResponseEntity<?> AppUpdate(Drugstoreinfo drugstoreinfo);

	ResponseEntity<?> getByTaskIdM(Integer id);

	ResponseEntity<?> getByTaskId(Integer id);

	ResponseEntity<?> getDrug(Integer id);



}
