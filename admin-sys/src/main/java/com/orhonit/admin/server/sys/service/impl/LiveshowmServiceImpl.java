package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.LiveshowmDao;
import com.orhonit.admin.server.sys.model.Liveshowm;
import com.orhonit.admin.server.sys.service.LiveshowmService;
@Service
public class LiveshowmServiceImpl implements LiveshowmService {
	@Autowired
    private LiveshowmDao liveshowmDao;

	@Override
	public void save(Liveshowm liveshowm) {
		// TODO Auto-generated method stub
		liveshowmDao.save(liveshowm);
	}

	@Override
	public Liveshowm getById(Long id) {
		// TODO Auto-generated method stub
		return liveshowmDao.getById(id);
	}

	@Override
	public void update(Liveshowm liveshowm) {
		// TODO Auto-generated method stub
		liveshowmDao.update(liveshowm);
	}

	@Override
	public TableResponse<Liveshowm> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Liveshowm> builder().countHandler(new CountHandler() {
            @Override
            public int count(TableRequest request) {
                return liveshowmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Liveshowm>() {

            @Override
            public List<Liveshowm> list(TableRequest request) {
                return liveshowmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		liveshowmDao.delete(id);
	}
}
