package com.orhonit.admin.server.sys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.StudyArticle;

public interface StudyArticleService {

	void save(StudyArticle studyArticle);

	void update(StudyArticle studyArticle);

	TableResponse<StudyArticle> list(TableRequest request);

	void delete(Long id);

	int studyArticlePass(Long id);

	int studyArticlePassFail(Long id, String reason);

	StudyArticle getId(Long id);

	List<StudyArticle> ten(long start);

	ResponseEntity<?> AppAdd(StudyArticle studyArticle);

	ResponseEntity<?> getAll();

	ResponseEntity<?> getByUid();

	ResponseEntity<?> addStudyArticle(Integer id);

}
