package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.CategoryDao;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.StudyVideoDao;
import com.orhonit.admin.server.sys.model.StudyVideo;
import com.orhonit.admin.server.sys.service.StudyVideoService;
@Service
public class StudyVideoServiceImpl implements StudyVideoService {
	@Autowired
    private StudyVideoDao studyVideoDao;
    
    @Autowired
    private ExpertinfoDao expertinfoDao;
    
    @Autowired
    private CategoryDao categoryDao;

	@Override
	public void save(StudyVideo studyVideo) {
		// TODO Auto-generated method stub
		studyVideoDao.save(studyVideo);
	}

	@Override
	public StudyVideo getById(Long id) {
		// TODO Auto-generated method stub
		return studyVideoDao.getById(id);
	}

	@Override
	public void update(StudyVideo studyVideo) {
		// TODO Auto-generated method stub
		studyVideoDao.update(studyVideo);
	}

	@Override
	public TableResponse<StudyVideo> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<StudyVideo> builder().countHandler(new CountHandler() {

	            @Override
	            public int count(TableRequest request) {
	                return studyVideoDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<StudyVideo>() {

	            @Override
	            public List<StudyVideo> list(TableRequest request) {
	               List<StudyVideo> studyVideoList= studyVideoDao.list(request.getParams(), request.getStart(), request.getLength());
	               for(StudyVideo studyVideo:studyVideoList) {
	            	   studyVideo.setName(expertinfoDao.ByUid(studyVideo.getUid()).getName()); 
	            	   studyVideo.setCategoryName(categoryDao.getByParentId(studyVideo.getCategoryId()).getName());
	               };
	               return studyVideoList;
	               
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		studyVideoDao.delete(id);
	}

	@Override
	public int studyArticlePass(Long id) {
		// TODO Auto-generated method stub
		StudyVideo studyVideo = studyVideoDao.getById(id);
		studyVideo.setStatus(1);
		return studyVideoDao.updatePass(studyVideo);
	}

	@Override
	public int studyArticlePassFail(Long id, String reason) {
		// TODO Auto-generated method stub
		StudyVideo studyVideo = studyVideoDao.getById(id);
    	studyVideo.setStatus(2);
    	studyVideo.setReason(reason);
    	return studyVideoDao.updateFail(studyVideo);
	}

	@Override
	public List<StudyVideo> ten(Long start) {
		// TODO Auto-generated method stub
		return studyVideoDao.ten(start);
	}

	@Override
	public StudyVideo frist() {
		// TODO Auto-generated method stub
		return studyVideoDao.frist();
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		try {
			List<StudyVideo> list = studyVideoDao.getAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> addStudyVideo(Integer id) {
		// TODO Auto-generated method stub
		try {
			studyVideoDao.addStudyVideo(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}
    
}
