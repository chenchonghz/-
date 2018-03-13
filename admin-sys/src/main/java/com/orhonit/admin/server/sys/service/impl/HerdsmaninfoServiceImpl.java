package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.HerdsmaninfoDao;
import com.orhonit.admin.server.sys.model.Herdsmaninfo;
import com.orhonit.admin.server.sys.service.HerdsmaninfoService;
@Service
public class HerdsmaninfoServiceImpl implements HerdsmaninfoService {
	@Autowired
	private HerdsmaninfoDao herdsmaninfoDao;

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		herdsmaninfoDao.delete(id);
	}

	@Override
	public TableResponse<Herdsmaninfo> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Herdsmaninfo> builder().countHandler(new CountHandler() {
            @Override
            public int count(TableRequest request) {
                return herdsmaninfoDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Herdsmaninfo>() {
            @Override
            public List<Herdsmaninfo> list(TableRequest request) {
                return herdsmaninfoDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void update(Herdsmaninfo herdsmaninfo) {
		// TODO Auto-generated method stub
		herdsmaninfoDao.update(herdsmaninfo);
	}

	@Override
	public Herdsmaninfo getByUid(int id) {
		// TODO Auto-generated method stub
		return herdsmaninfoDao.getByUid(id);
	}

	@Override
	public Herdsmaninfo getById(Long id) {
		// TODO Auto-generated method stub
		return herdsmaninfoDao.getById(id);
	}

	@Override
	public void save(Herdsmaninfo herdsmaninfo) {
		// TODO Auto-generated method stub
		 herdsmaninfoDao.save(herdsmaninfo);
	}
	 
}
