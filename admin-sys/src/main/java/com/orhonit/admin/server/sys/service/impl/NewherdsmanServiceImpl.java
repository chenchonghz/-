package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.NewherdsmanDao;
import com.orhonit.admin.server.sys.model.Newherdsman;
import com.orhonit.admin.server.sys.service.NewherdsmanService;

@Service
public class NewherdsmanServiceImpl implements NewherdsmanService {
	@Autowired
    private NewherdsmanDao newherdsmanDao;

	@Override
	public void save(Newherdsman newherdsman) {
		// TODO Auto-generated method stub
		newherdsmanDao.save(newherdsman);
	}

	@Override
	public Newherdsman getById(Long id) {
		// TODO Auto-generated method stub
		return newherdsmanDao.getById(id);
	}

	@Override
	public void update(Newherdsman newherdsman) {
		// TODO Auto-generated method stub
		newherdsmanDao.update(newherdsman);
	}

	@Override
	public TableResponse<Newherdsman> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<Newherdsman> builder().countHandler(new CountHandler() {

	            @Override
	            public int count(TableRequest request) {
	                return newherdsmanDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<Newherdsman>() {

	            @Override
	            public List<Newherdsman> list(TableRequest request) {
	                return newherdsmanDao.list(request.getParams(), request.getStart(), request.getLength());
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		newherdsmanDao.delete(id);
	}

	@Override
	public Newherdsman getByUid(int id) {
		// TODO Auto-generated method stub
		return newherdsmanDao.getByUid(id);
	}

	@Override
	public Newherdsman getByUidm(int id) {
		// TODO Auto-generated method stub
		return newherdsmanDao.getByUidm(id);
	}

	@Override
	public Newherdsman getUid(Long id) {
		// TODO Auto-generated method stub
		return newherdsmanDao.getUid(id);
	}
}
