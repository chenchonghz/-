package com.orhonit.admin.server.sys.service;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Comment;

public interface CommentService {

	void save(Comment comment);

	Comment getById(Long id);

	void update(Comment comment);

	TableResponse<Comment> list(TableRequest request);

	void delete(Long id);

	ResponseEntity<?> saveCom(Comment comment);

	ResponseEntity<?> examine(Integer id);

	ResponseEntity<?> getList();

	ResponseEntity<?> getCBycid(Integer cateId, Integer childrenId);

}
