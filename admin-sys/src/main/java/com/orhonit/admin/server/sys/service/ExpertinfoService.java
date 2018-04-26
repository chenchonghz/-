package com.orhonit.admin.server.sys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.model.Prescription;
import com.orhonit.admin.server.sys.model.Prescriptionm;
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.model.Taskm;

public interface ExpertinfoService {

	void save(Expertinfo expertinfo);

	Expertinfo getById(Long id);

	Expertinfo ByUid(int id);

	void update(Expertinfo expertinfo);

	int getUid(Long id);

	int getFailUid(Long id);

	TableResponse<Expertinfo> list(TableRequest request);

	void delete(Long id);

	List<Expertinfo> three();

	List<Expertinfo> ten(long start);

	void createTask(Long id);

	ResponseEntity<?> getByUid(int id);

	ResponseEntity<?> saveExpertinfo(Expertinfo expertinfo);

	ResponseEntity<?> AppUpdate(Expertinfo expertinfo);

	ResponseEntity<?> AppList();

	void examinem(Taskm taskm, List<Prescriptionm> list);

	void examine(Task task, List<Prescription> list);

	ResponseEntity<?> CreateByPhone(String phone);

	ResponseEntity<?> CreatemByPhone(String phone);

}
