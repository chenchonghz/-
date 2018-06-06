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
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.StudyarticlemDao;
import com.orhonit.admin.server.sys.model.Studyarticlem;
import com.orhonit.admin.server.sys.service.StudyarticlemService;
import com.orhonit.admin.server.sys.utils.UserUtil;

@Service
public class StudyarticlemServiceImpl implements StudyarticlemService {
	@Autowired
    private StudyarticlemDao studyarticlemDao;
	
    @Autowired
    private ExpertinfoDao expertinfoDao;
    

	@Override
	public void save(Studyarticlem studyarticlem) {
		// TODO Auto-generated method stub
		studyarticlemDao.save(studyarticlem);
	}

	@Override
	public Studyarticlem getById(Long id) {
		// TODO Auto-generated method stub
		return studyarticlemDao.getId(id);
	}

	@Override
	public void update(Studyarticlem studyarticlem) {
		// TODO Auto-generated method stub
		studyarticlemDao.update(studyarticlem);
	}

	@Override
	public TableResponse<Studyarticlem> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Studyarticlem> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return studyarticlemDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Studyarticlem>() {

            @Override
            public List<Studyarticlem> list(TableRequest request) {
                List<Studyarticlem> studyarticlems = studyarticlemDao.list(request.getParams(), request.getStart(), request.getLength());
                for (Studyarticlem studyarticlem : studyarticlems) {
                	studyarticlem.setName(expertinfoDao.ByUid(studyarticlem.getUid()).getName());
				}
                return studyarticlems;
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		studyarticlemDao.delete(id);
	}

	@Override
	public List<Studyarticlem> ten(long l) {
		// TODO Auto-generated method stub
		return studyarticlemDao.ten(l);
	}

	@Override
	public ResponseEntity<?> AppAdd(Studyarticlem studyArticle) {
		// TODO Auto-generated method stub
		try {
			Long uId = UserUtil.getCurrentUser().getId();
			studyArticle.setUid(Integer.parseInt(uId.toString()));
			studyArticle.setStatus(0);
			studyArticle.setClicks(0);
			studyarticlemDao.save(studyArticle);
			return ResponseEntity.ok(null);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		try {
			List<Studyarticlem> list =studyarticlemDao.getAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> getByUid() {
		// TODO Auto-generated method stub
		try {
			Long uid = UserUtil.getCurrentUser().getId();
			List<Studyarticlem> list =studyarticlemDao.getByUid(uid);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> addStudyArticlem(Integer id) {
		// TODO Auto-generated method stub
		try {
			studyarticlemDao.addStudyArticlem(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public int studyArticlePass(Long id) {
		// TODO Auto-generated method stub
		Studyarticlem studyarticlem = studyarticlemDao.getById(id);
		studyarticlem.setStatus(1);
		return studyarticlemDao.updatePass(studyarticlem);
	}

	@Override
	public int studyArticlePassFail(Long id, String reason) {
		// TODO Auto-generated method stub
		Studyarticlem studyarticlem = studyarticlemDao.getById(id);
		studyarticlem.setStatus(2);
		studyarticlem.setReason(reason);
		return studyarticlemDao.updateFail(studyarticlem);
	}
	
}
