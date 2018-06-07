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
import com.orhonit.admin.server.sys.dao.StudyvideomDao;
import com.orhonit.admin.server.sys.model.Studyvideom;
import com.orhonit.admin.server.sys.service.StudyvideomService;

@Service
public class StudyvideomServiceImpl implements StudyvideomService {
	@Autowired
    private StudyvideomDao studyvideomDao;
	@Autowired
	private ExpertinfoDao expertinfoDao;
	
	@Override
	public void save(Studyvideom studyvideom) {
		// TODO Auto-generated method stub
		studyvideomDao.save(studyvideom);
	}

	@Override
	public Studyvideom getById(Long id) {
		// TODO Auto-generated method stub
		return studyvideomDao.getId(id);
	}

	@Override
	public void update(Studyvideom studyvideom) {
		// TODO Auto-generated method stub
		studyvideomDao.update(studyvideom);
	}

	@Override
	public TableResponse<Studyvideom> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Studyvideom> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return studyvideomDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Studyvideom>() {

            @Override
            public List<Studyvideom> list(TableRequest request) {
                List<Studyvideom> list = studyvideomDao.list(request.getParams(), request.getStart(), request.getLength());
                for (Studyvideom studyvideom : list) {
					studyvideom.setName(expertinfoDao.ByUid(studyvideom.getUid()).getName());
				}
                return list;
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		studyvideomDao.delete(id);
	}

	@Override
	public List<Studyvideom> frist() {
		// TODO Auto-generated method stub
		return studyvideomDao.frist();
	}

	@Override
	public List<Studyvideom> ten(long l) {
		// TODO Auto-generated method stub
		return studyvideomDao.ten(l);
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		try {
			List<Studyvideom> list = studyvideomDao.getAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> addStudyVideom(Integer id) {
		// TODO Auto-generated method stub
		try {
			studyvideomDao.addStudyVideom(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public int studyArticlePass(Long id) {
		// TODO Auto-generated method stub
		Studyvideom studyvideom = studyvideomDao.getById(id);
		studyvideom.setStatus(1);
		return studyvideomDao.updatePass(studyvideom);
	}

	@Override
	public int studyArticlePassFail(Long id, String reason) {
		// TODO Auto-generated method stub
		Studyvideom studyvideom = studyvideomDao.getById(id);
		studyvideom.setStatus(2);
		studyvideom.setReason(reason);
		return studyvideomDao.updateFail(studyvideom);
	}
}
