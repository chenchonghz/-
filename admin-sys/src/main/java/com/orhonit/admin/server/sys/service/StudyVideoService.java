package com.orhonit.admin.server.sys.service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.StudyVideo;

public interface StudyVideoService {

	void save(StudyVideo studyVideo);

	StudyVideo getById(Long id);

	void update(StudyVideo studyVideo);

	TableResponse<StudyVideo> list(TableRequest request);

	void delete(Long id);

	int studyArticlePass(Long id);

	int studyArticlePassFail(Long id, String reason);

}
