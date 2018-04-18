package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.TaskmDao;
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.service.TaskmService;
@Service
public class TaskmServiceImpl implements TaskmService {
	@Autowired
    private TaskmDao taskmDao;

	@Override
	public void save(Taskm taskm) {
		// TODO Auto-generated method stub
		taskmDao.save(taskm);
	}

	@Override
	public Taskm getById(Long id) {
		// TODO Auto-generated method stub
		return taskmDao.getById(id);
	}

	@Override
	public void update(Taskm taskm) {
		// TODO Auto-generated method stub
		taskmDao.update(taskm);
	}

	@Override
	public TableResponse<Taskm> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Taskm> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return taskmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Taskm>() {

            @Override
            public List<Taskm> list(TableRequest request) {
                return taskmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		taskmDao.delete(id);
	}

	@Override
	public List<Taskm> ten(long l) {
		// TODO Auto-generated method stub
		return taskmDao.ten(l);
	}

}
