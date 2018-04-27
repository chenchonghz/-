package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.TaskDao;
import com.orhonit.admin.server.sys.dao.UserDao;
import com.orhonit.admin.server.sys.dto.TaskDto;
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.service.TaskService;
import com.orhonit.admin.server.sys.utils.JpushClientUtil;
import com.orhonit.admin.server.sys.utils.UserUtil;
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

	@Override
	public List<Task> ten(long start) {
		// TODO Auto-generated method stub
		return taskDao.ten(start);
	}

	@Override
	public ResponseEntity<?> AppList() {
		// TODO Auto-generated method stub
		try {
			User user = UserUtil.getCurrentUser();
			if(user.getType() == 1){
				List<TaskDto> list = taskDao.selectByHid(user.getId());
				return ResponseEntity.ok(list);
			}else if(user.getType() == 2){
				List<TaskDto> list = taskDao.selectByEid(user.getId());
				return ResponseEntity.ok(list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
		return ResponseEntity.ok(null);
	}

	@Override
	public ResponseEntity<?> AppDelete(Long id) {
		// TODO Auto-generated method stub
		try {
			taskDao.AppDelete(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}
	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<?> AppAddTask(Task task) {
		// TODO Auto-generated method stub
		try {
			task.setHerdsmanId(Integer.parseInt(UserUtil.getCurrentUser().getId().toString()));
			taskDao.save(task);
			User user = userDao.getById(Long.parseLong(task.getExpertId().toString()));
			JpushClientUtil.sendToRegistrationId(user.getRegsId(), "通知", "你有一个新的诊断待完善", "1", "2");
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}
}
