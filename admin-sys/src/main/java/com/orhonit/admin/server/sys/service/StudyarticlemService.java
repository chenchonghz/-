package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Studyarticlem;

public interface StudyarticlemService {

	void save(Studyarticlem studyarticlem);

	Studyarticlem getById(Long id);

	void update(Studyarticlem studyarticlem);

	TableResponse<Studyarticlem> list(TableRequest request);

	void delete(Long id);

}
