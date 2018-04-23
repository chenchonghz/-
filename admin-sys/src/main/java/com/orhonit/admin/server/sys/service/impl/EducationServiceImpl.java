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
import com.orhonit.admin.server.sys.dao.EducationDao;
import com.orhonit.admin.server.sys.model.Education;
import com.orhonit.admin.server.sys.service.EducationService;

@Service
public class EducationServiceImpl implements EducationService{
	 @Autowired
	 private EducationDao educationDao;

	@Override
	public void save(Education education) {
		// TODO Auto-generated method stub
		educationDao.save(education);
	}

	@Override
	public Education getById(Long id) {
		// TODO Auto-generated method stub
		return educationDao.getById(id);
	}

	@Override
	public void update(Education education) {
		// TODO Auto-generated method stub
		educationDao.update(education);
	}

	@Override
	public TableResponse<Education> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<Education> builder().countHandler(new CountHandler() {
	            @Override
	            public int count(TableRequest request) {
	                return educationDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<Education>() {
	            @Override
	            public List<Education> list(TableRequest request) {
	                return educationDao.list(request.getParams(), request.getStart(), request.getLength());
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		educationDao.delete(id);
	}

	@Override
	public ResponseEntity<?> getList() {
		// TODO Auto-generated method stub
		try {
			List<Education> list = educationDao.getList();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

}
