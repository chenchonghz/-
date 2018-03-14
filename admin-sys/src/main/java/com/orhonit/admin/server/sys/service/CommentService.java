package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Comment;

public interface CommentService {

	void save(Comment comment);

	Comment getById(Long id);

	void update(Comment comment);

	TableResponse<Comment> list(TableRequest request);

	void delete(Long id);

}
