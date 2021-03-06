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
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.IllnesscategorymDao;
import com.orhonit.admin.server.sys.dao.NewherdsmanDao;
import com.orhonit.admin.server.sys.dao.TaskmDao;
import com.orhonit.admin.server.sys.dao.UserDao;
import com.orhonit.admin.server.sys.dto.TaskmDto;
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.service.TaskmService;
import com.orhonit.admin.server.sys.utils.JpushClientUtil;
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
		return taskmDao.getId(id);
	}

	@Override
	public void update(Taskm taskm) {
		// TODO Auto-generated method stub
		taskmDao.update(taskm);
	}
	@Autowired
	private NewherdsmanDao newherdsmanDao;
	@Autowired
	private ExpertinfoDao expertinfoDao;
	@Autowired
	private IllnesscategorymDao illnesscategorymDao;
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
                List<Taskm> list = taskmDao.list(request.getParams(), request.getStart(), request.getLength());
                for (Taskm taskm : list) {
                	taskm.setNameE(expertinfoDao.getUId(Long.parseLong(taskm.getExpertId().toString())).getNameMeng());
                	taskm.setNameH(newherdsmanDao.getByUid(taskm.getHerdsmanId()).getNameMeng());
                	taskm.setNameI(illnesscategorymDao.getById(Long.parseLong(taskm.getIllnessCategoryId().toString())).getName());
				}
                return list;
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
				List<TaskmDto> list = taskmDao.selectByHid(user.getId());
				return ResponseEntity.ok(list);
			}else if(user.getType() == 2){
				List<TaskmDto> list = taskmDao.selectByEid(user.getId());
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
	@Autowired
	private UserDao userDao;
	@Override
	public ResponseEntity<?> AppAddTaskm(Taskm taskm) {
		// TODO Auto-generated method stub
		try {
			taskm.setHerdsmanId(Integer.parseInt(UserUtil.getCurrentUser().getId().toString()));
			taskm.setStatus(1);
			taskm.setType(1);
			taskmDao.save(taskm);
			String eid = taskm.getExpertId().toString();
			System.err.println(eid);
			User user = userDao.getById(Long.parseLong(eid));
			JpushClientUtil.sendToRegistrationId(user.getRegsId(), "通知", "你有一个新的诊断待完善", "1", "2");
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		try {
			List<Taskm> list = taskmDao.getAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public int updateGood(Long id, Integer good) {
		// TODO Auto-generated method stub
		Taskm taskm = taskmDao.getById(id);
		taskm.setGood(good);
		return taskmDao.updateGood(taskm);
	}

}
