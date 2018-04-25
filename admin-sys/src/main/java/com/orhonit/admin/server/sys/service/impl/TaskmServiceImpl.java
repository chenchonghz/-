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
import com.orhonit.admin.server.sys.dao.TaskmDao;
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.service.TaskmService;
import com.orhonit.admin.server.sys.utils.UserUtil;
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

	@Override
	public ResponseEntity<?> AppList() {
		// TODO Auto-generated method stub
		try {
			User user = UserUtil.getCurrentUser();
			if(user.getType() == 1){
				List<Taskm> list = taskmDao.selectByHid(user.getId());
				return ResponseEntity.ok(list);
			}else if(user.getType() == 2){
				List<Taskm> list = taskmDao.selectByEid(user.getId());
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
			taskmDao.AppDelete(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body(null);
		}
	}

	@Override
	public ResponseEntity<?> AppAddTaskm(Taskm taskm) {
		// TODO Auto-generated method stub
		try {
			taskm.setHerdsmanId(Integer.parseInt(UserUtil.getCurrentUser().getId().toString()));
			taskmDao.save(taskm);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

}
