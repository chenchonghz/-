package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.IllnesscategoryDao;
import com.orhonit.admin.server.sys.model.Illnesscategory;
import com.orhonit.admin.server.sys.service.illnesscategoryService;
@Service
public class illnesscategoryServiceImpl implements illnesscategoryService {
	@Autowired
    private IllnesscategoryDao illnesscategoryDao;

	@Override
	public void save(Illnesscategory illnesscategory) {
		// TODO Auto-generated method stub
		illnesscategoryDao.save(illnesscategory);
	}

	@Override
	public Illnesscategory getById(Long id) {
		// TODO Auto-generated method stub
		return illnesscategoryDao.getById(id);
	}

	@Override
	public void update(Illnesscategory illnesscategory) {
		// TODO Auto-generated method stub
		illnesscategoryDao.update(illnesscategory);
	}

	@Override
	public TableResponse<Illnesscategory> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<Illnesscategory> builder().countHandler(new CountHandler() {

	            @Override
	            public int count(TableRequest request) {
	                return illnesscategoryDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<Illnesscategory>() {

	            @Override
	            public List<Illnesscategory> list(TableRequest request) {
	                return illnesscategoryDao.list(request.getParams(), request.getStart(), request.getLength());
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		illnesscategoryDao.delete(id);
	}
}
