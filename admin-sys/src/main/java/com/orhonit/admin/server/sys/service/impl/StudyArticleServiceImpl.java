package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.CategoryDao;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.StudyArticleDao;
import com.orhonit.admin.server.sys.model.StudyArticle;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.service.StudyArticleService;
import com.orhonit.admin.server.sys.utils.UserUtil;
@Service
public class StudyArticleServiceImpl implements StudyArticleService {
	@Autowired
    private StudyArticleDao studyArticleDao;
    
    @Autowired
    private ExpertinfoDao expertinfoDao;
    
    @Autowired
    private CategoryDao categoryDao;

	@Override
	public void save(StudyArticle studyArticle) {
		// TODO Auto-generated method stub
		User user = UserUtil.getCurrentUser();
		studyArticle.setUid(Integer.parseInt(user.getId().toString()));
		studyArticleDao.save(studyArticle);
	}

	@Override
	public void update(StudyArticle studyArticle) {
		// TODO Auto-generated method stub
		studyArticleDao.update(studyArticle);
	}

	@Override
	public TableResponse<StudyArticle> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<StudyArticle> builder().countHandler(new CountHandler() {
	            @Override
	            public int count(TableRequest request) {
	                return studyArticleDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<StudyArticle>() {
	            @Override
	            public List<StudyArticle> list(TableRequest request) {
	            	List<StudyArticle> studyArticleList=studyArticleDao.list(request.getParams(), request.getStart(), request.getLength());
	            		for(StudyArticle studyArticle : studyArticleList) {
	            		studyArticle.setName(expertinfoDao.ByUid(studyArticle.getUid()).getName()); 
	            		studyArticle.setCategoryName(categoryDao.getByParentId(studyArticle.getCategoryId()).getName());	
	            		}
	            		return studyArticleList;
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		studyArticleDao.delete(id);
	}

	@Override
	@Transactional
	public int studyArticlePass(Long id) {
		// TODO Auto-generated method stub
		StudyArticle studyArticle = studyArticleDao.getById(id);
		studyArticle.setStatus(1);
		return studyArticleDao.updatePass(studyArticle);
	}

	@Override
	@Transactional
	public int studyArticlePassFail(Long id, String reason) {
		// TODO Auto-generated method stub
		StudyArticle studyArticle = studyArticleDao.getById(id);
		studyArticle.setStatus(2);
		studyArticle.setReason(reason);
		return studyArticleDao.updateFail(studyArticle);
	}

	@Override
	public StudyArticle getId(Long id) {
		// TODO Auto-generated method stub
		return studyArticleDao.getId(id);
	}
    
}
