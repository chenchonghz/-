package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.service.ExpertinfoService;
@Service
public class ExpertinfoServiceImpl implements ExpertinfoService {
	 @Autowired
	 private ExpertinfoDao expertinfoDao;

	@Override
	public void save(Expertinfo expertinfo) {
		// TODO Auto-generated method stub
		expertinfoDao.save(expertinfo);
	}

	@Override
	public Expertinfo getById(Long id) {
		// TODO Auto-generated method stub
		return expertinfoDao.getById(id);
	}

	@Override
	public Expertinfo ByUid(int id) {
		// TODO Auto-generated method stub
		return expertinfoDao.ByUid(id);
	}

	@Override
	public void update(Expertinfo expertinfo) {
		// TODO Auto-generated method stub
		expertinfoDao.update(expertinfo);
	}

	@Override
	@Transactional
	public int getUid(Long id) {
		// TODO Auto-generated method stub
		Expertinfo expert=expertinfoDao.ById(id);
    	expert.setStatus(1);
    	return expertinfoDao.update(expert);
	}

	@Override
	public int getFailUid(Long id) {
		// TODO Auto-generated method stub
		Expertinfo expert=expertinfoDao.ById(id);
    	expert.setStatus(2);
    	return expertinfoDao.update(expert);
	}

	@Override
	public TableResponse<Expertinfo> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Expertinfo> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return expertinfoDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Expertinfo>() {

            @Override
            public List<Expertinfo> list(TableRequest request) {
                return expertinfoDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		expertinfoDao.delete(id);
	}
	 
}
