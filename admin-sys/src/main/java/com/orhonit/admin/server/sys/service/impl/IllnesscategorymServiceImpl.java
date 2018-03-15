package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.IllnesscategorymDao;
import com.orhonit.admin.server.sys.model.Illnesscategorym;
import com.orhonit.admin.server.sys.service.IllnesscategorymService;
@Service
public class IllnesscategorymServiceImpl implements IllnesscategorymService {
	@Autowired
    private IllnesscategorymDao illnesscategorymDao;

	@Override
	public void save(Illnesscategorym illnesscategorym) {
		// TODO Auto-generated method stub
		illnesscategorymDao.save(illnesscategorym);
	}

	@Override
	public Illnesscategorym getById(Long id) {
		// TODO Auto-generated method stub
		return illnesscategorymDao.getById(id);
	}

	@Override
	public void update(Illnesscategorym illnesscategorym) {
		// TODO Auto-generated method stub
		illnesscategorymDao.update(illnesscategorym);
	}

	@Override
	public TableResponse<Illnesscategorym> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Illnesscategorym> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return illnesscategorymDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Illnesscategorym>() {

            @Override
            public List<Illnesscategorym> list(TableRequest request) {
                return illnesscategorymDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		illnesscategorymDao.delete(id);
	}
	
}
