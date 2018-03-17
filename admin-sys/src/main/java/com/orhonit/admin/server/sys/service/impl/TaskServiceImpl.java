package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.TaskDao;
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
    private TaskDao taskDao;

	@Override
	public void save(Task task) {
		// TODO Auto-generated method stub
		taskDao.save(task);
	}

	@Override
	public Task getById(Long id) {
		// TODO Auto-generated method stub
		return taskDao.getById(id);
	}

	@Override
	public void update(Task task) {
		// TODO Auto-generated method stub
		taskDao.update(task);
	}

	@Override
	public TableResponse<Task> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Task> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return taskDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Task>() {

            @Override
            public List<Task> list(TableRequest request) {
                return taskDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		taskDao.delete(id);
	}
}