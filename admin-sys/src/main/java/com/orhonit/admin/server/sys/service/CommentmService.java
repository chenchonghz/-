package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Commentm;

public interface CommentmService {

	void save(Commentm commentm);

	Commentm getById(Long id);

	void update(Commentm commentm);

	TableResponse<Commentm> list(TableRequest request);

	void delete(Long id);

}
