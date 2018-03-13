package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.DistrictDao;
import com.orhonit.admin.server.sys.model.District;
import com.orhonit.admin.server.sys.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {
	@Autowired
	private DistrictDao districtDao;

	@Override
	public void save(District district) {
		// TODO Auto-generated method stub
		districtDao.save(district);
	}

	@Override
	public List<District> getByUpid(int i) {
		// TODO Auto-generated method stub
		return districtDao.getByUpid(i);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		districtDao.delete(id);
	}

	@Override
	public District getById(Long id) {
		// TODO Auto-generated method stub
		return districtDao.getById(id);
	}

	@Override
	public void update(District district) {
		// TODO Auto-generated method stub
		districtDao.update(district);
	}

	@Override
	public TableResponse<District> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<District> builder().countHandler(new CountHandler() {
            @Override
            public int count(TableRequest request) {
                return districtDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<District>() {

            @Override
            public List<District> list(TableRequest request) {
                return districtDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}
	  
}
