package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.CategorymDao;
import com.orhonit.admin.server.sys.model.Categorym;
import com.orhonit.admin.server.sys.service.CategorymService;

@Service
public class CategorymServiceImpl implements CategorymService {
	 @Autowired
	 private CategorymDao categorymDao;

	@Override
	public void save(Categorym categorym) {
		// TODO Auto-generated method stub
		categorymDao.save(categorym);
	}

	@Override
	public Categorym getById(Long id) {
		// TODO Auto-generated method stub
		return categorymDao.getById(id);
	}

	@Override
	public void update(Categorym categorym) {
		// TODO Auto-generated method stub
		categorymDao.update(categorym);
	}

	@Override
	public TableResponse<Categorym> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Categorym> builder().countHandler(new CountHandler() {
            @Override
            public int count(TableRequest request) {
                return categorymDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Categorym>() {
            @Override
            public List<Categorym> list(TableRequest request) {
                return categorymDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		categorymDao.delete(id);
	}

}
