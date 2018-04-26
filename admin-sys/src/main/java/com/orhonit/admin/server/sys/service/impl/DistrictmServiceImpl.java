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
import com.orhonit.admin.server.sys.dao.DistrictmDao;
import com.orhonit.admin.server.sys.model.Districtm;
import com.orhonit.admin.server.sys.service.DistrictmService;


@Service
public class DistrictmServiceImpl implements DistrictmService {
	@Autowired
    private DistrictmDao districtmDao;

	@Override
	public void save(Districtm districtm) {
		// TODO Auto-generated method stub
		districtmDao.save(districtm);
	}

	@Override
	public List<Districtm> getByUpid(int i) {
		// TODO Auto-generated method stub
		return districtmDao.getByUpid(i);
	}

	@Override
	public Districtm getById(Long id) {
		// TODO Auto-generated method stub
		return districtmDao.getById(id);
	}

	@Override
	public void update(Districtm districtm) {
		// TODO Auto-generated method stub
		districtmDao.update(districtm);
	}

	@Override
	public TableResponse<Districtm> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<Districtm> builder().countHandler(new CountHandler() {

	            @Override
	            public int count(TableRequest request) {
	                return districtmDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<Districtm>() {

	            @Override
	            public List<Districtm> list(TableRequest request) {
	                return districtmDao.list(request.getParams(), request.getStart(), request.getLength());
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		districtmDao.delete(id);
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		try {
			List<Districtm> list = districtmDao.getAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}
	
	
	
}
