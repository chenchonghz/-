package com.orhonit.admin.server.sys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Expertinfo;

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

}
