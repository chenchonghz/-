package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.HerdsmaninfomDao;
import com.orhonit.admin.server.sys.model.Herdsmaninfom;
import com.orhonit.admin.server.sys.service.HerdsmaninfomService;
@Service
public class HerdsmaninfomServiceImpl implements HerdsmaninfomService {
	@Autowired
    private HerdsmaninfomDao herdsmaninfomDao;

	@Override
	public void save(Herdsmaninfom herdsmaninfom) {
		// TODO Auto-generated method stub
		herdsmaninfomDao.save(herdsmaninfom);
	}

	@Override
	public Herdsmaninfom getById(Long id) {
		// TODO Auto-generated method stub
		return herdsmaninfomDao.getById(id);
	}

	@Override
	public void update(Herdsmaninfom herdsmaninfom) {
		// TODO Auto-generated method stub
		herdsmaninfomDao.update(herdsmaninfom);
	}

	@Override
	public TableResponse<Herdsmaninfom> list(TableRequest request) {
		// TODO Auto-generated method stub
		  return TableRequestHandler.<Herdsmaninfom> builder().countHandler(new CountHandler() {
            @Override
            public int count(TableRequest request) {
                return herdsmaninfomDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Herdsmaninfom>() {

            @Override
            public List<Herdsmaninfom> list(TableRequest request) {
                return herdsmaninfomDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		herdsmaninfomDao.delete(id);
	}
}
