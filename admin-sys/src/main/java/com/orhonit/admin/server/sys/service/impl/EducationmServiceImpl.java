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
import com.orhonit.admin.server.sys.dao.EducationmDao;
import com.orhonit.admin.server.sys.model.Educationm;
import com.orhonit.admin.server.sys.service.EducationmService;

@Service
public class EducationmServiceImpl implements EducationmService {
	@Autowired
    private EducationmDao educationmDao;

	@Override
	public void save(Educationm educationm) {
		// TODO Auto-generated method stub
		educationmDao.save(educationm);
	}

	@Override
	public Educationm getById(Long id) {
		// TODO Auto-generated method stub
		return educationmDao.getById(id);
	}

	@Override
	public void update(Educationm educationm) {
		// TODO Auto-generated method stub
		educationmDao.update(educationm);
	}

	@Override
	public TableResponse<Educationm> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Educationm> builder().countHandler(new CountHandler() {
            @Override
            public int count(TableRequest request) {
                return educationmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Educationm>() {
            @Override
            public List<Educationm> list(TableRequest request) {
                return educationmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		educationmDao.delete(id);
	}

	@Override
	public ResponseEntity<?> getList() {
		// TODO Auto-generated method stub
		try {
			List<Educationm> list = educationmDao.getList();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}
	
}
