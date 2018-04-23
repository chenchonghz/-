package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.SlidemDao;
import com.orhonit.admin.server.sys.model.Slide;
import com.orhonit.admin.server.sys.model.Slidem;
import com.orhonit.admin.server.sys.service.SlidemService;
@Service
public class SlidemServiceImpl implements SlidemService {
	@Autowired
    private SlidemDao slidemDao;

	@Override
	public void save(Slidem slidem) {
		// TODO Auto-generated method stub
		slidemDao.save(slidem);
	}

	@Override
	public Slidem getById(Long id) {
		// TODO Auto-generated method stub
		return slidemDao.getById(id);
	}

	@Override
	public void update(Slidem slidem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TableResponse<Slidem> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Slidem> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return slidemDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Slidem>() {

            @Override
            public List<Slidem> list(TableRequest request) {
                return slidemDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		slidemDao.delete(id);
	}

	@Override
	public List<Slide> getSlidemList() {
		// TODO Auto-generated method stub
		return slidemDao.getSlidemList();
	}
}
