package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Feedback;

public interface FeedbackService {

	void save(Feedback feedback);

	Feedback getById(Long id);

	void update(Feedback feedback);

	TableResponse<Feedback> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> Appsave(Feedback feedback);

}
