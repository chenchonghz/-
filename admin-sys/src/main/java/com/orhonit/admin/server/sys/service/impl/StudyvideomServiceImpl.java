package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.StudyvideomDao;
import com.orhonit.admin.server.sys.model.Studyvideom;
import com.orhonit.admin.server.sys.service.StudyvideomService;

@Service
public class StudyvideomServiceImpl implements StudyvideomService {
	@Autowired
    private StudyvideomDao studyvideomDao;

	@Override
	public void save(Studyvideom studyvideom) {
		// TODO Auto-generated method stub
		studyvideomDao.save(studyvideom);
	}

	@Override
	public Studyvideom getById(Long id) {
		// TODO Auto-generated method stub
		return studyvideomDao.getById(id);
	}

	@Override
	public void update(Studyvideom studyvideom) {
		// TODO Auto-generated method stub
		studyvideomDao.update(studyvideom);
	}

	@Override
	public TableResponse<Studyvideom> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Studyvideom> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return studyvideomDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Studyvideom>() {

            @Override
            public List<Studyvideom> list(TableRequest request) {
                return studyvideomDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		studyvideomDao.delete(id);
	}

	@Override
	public Studyvideom frist() {
		// TODO Auto-generated method stub
		return studyvideomDao.frist();
	}

	@Override
	public List<Studyvideom> ten(long l) {
		// TODO Auto-generated method stub
		return studyvideomDao.ten(l);
	}
}
