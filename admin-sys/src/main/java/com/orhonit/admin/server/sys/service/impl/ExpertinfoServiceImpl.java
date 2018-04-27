package com.orhonit.admin.server.sys.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.UserDao;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.model.Prescription;
import com.orhonit.admin.server.sys.model.Prescriptionm;
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.model.Videoconnect;
import com.orhonit.admin.server.sys.service.ExpertinfoService;
import com.orhonit.admin.server.sys.service.PrescriptionService;
import com.orhonit.admin.server.sys.service.PrescriptionmService;
import com.orhonit.admin.server.sys.service.TaskService;
import com.orhonit.admin.server.sys.service.TaskmService;
import com.orhonit.admin.server.sys.service.UserService;
import com.orhonit.admin.server.sys.service.VideoconnectService;
import com.orhonit.admin.server.sys.utils.JpushClientUtil;
import com.orhonit.admin.server.sys.utils.UserUtil;
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

	@Override
	public List<Expertinfo> three() {
		// TODO Auto-generated method stub
		return expertinfoDao.three();
	}

	@Override
	public List<Expertinfo> ten(long start) {
		// TODO Auto-generated method stub
		return expertinfoDao.ten(start);
	}
	@Autowired
	private VideoconnectService videoconnectService;
	@Autowired
	private TaskmService taskmService;
	@Autowired
	private TaskService taskService;
	/**
	 * @author: 孙少辉
	 * @data: 2018年4月18日
	 * @param id
	 * @see com.orhonit.admin.server.sys.service.ExpertinfoService#createTask(java.lang.Long)
	 * @Description: 视频诊断完毕后根据视频主键生成诊断表 
	 */
	@Override
	public void createTask(Long id) {
		// TODO Auto-generated method stub
		Videoconnect videoconnect = videoconnectService.getById(id);
		if(videoconnect.getType() == 1){
			Taskm taskm = new Taskm();
			taskm.setExpertId(videoconnect.getEid());
			taskm.setHerdsmanId(videoconnect.getHid());
			taskm.setStatus(1);
			taskm.setType(2);
			taskmService.save(taskm);
		}else{
			Task task = new Task();
			task.setExpertId(videoconnect.getEid());
			task.setHerdsmanId(videoconnect.getHid());
			task.setStatus(1);
			task.setType(2);
			taskService.save(task);
		}
	}

	/**
	 * @author: 孙少辉
	 * @data: 2018年4月21日
	 * @param id
	 * @return
	 * @see com.orhonit.admin.server.sys.service.ExpertinfoService#getByUid(int)
	 * @Description: 手机端根据uid拿到专家的详情信息，不存在返回401  
	 */
	@Override
	public ResponseEntity<?> getByUid(int id) {
		// TODO Auto-generated method stub
		Expertinfo expertinfo = expertinfoDao.ByUid(id);
		System.out.println(expertinfo);
		if(expertinfo != null){
			return ResponseEntity.ok(expertinfo);
		}else{
			return ResponseEntity.status(401).body("信息未完善");
		}
	}

	@Override
	public ResponseEntity<?> saveExpertinfo(Expertinfo expertinfo) {
		// TODO Auto-generated method stub
		try {
			User user = UserUtil.getCurrentUser();
			expertinfo.setUid(Integer.parseInt(user.getId().toString()));
			expertinfo.setStatus(0);
			expertinfo.setState(1);
			expertinfo.setDealingProblems(0);
			expertinfo.setActivity(0);
			expertinfo.setVideoDiagnosis(0);
			expertinfo.setPhoneDiagnosis(0);
			expertinfo.setCreateTime(new Date());
			expertinfo.setUpdateTime(new Date());
			expertinfoDao.save(expertinfo);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
		
	}

	@Override
	public ResponseEntity<?> AppUpdate(Expertinfo expertinfo) {
		// TODO Auto-generated method stub
		try {
			expertinfo.setStatus(0);
			expertinfo.setUpdateTime(new Date());
			expertinfoDao.AppUpdate(expertinfo);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("系统错误");
		}
	}

	@Override
	public ResponseEntity<?> AppList() {
		// TODO Auto-generated method stub
		try {
			List<Expertinfo> list= expertinfoDao.AppList();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}
	@Autowired
	private UserDao userDao;

	@Autowired
	private PrescriptionService prescriptionService;
	@Autowired
	private PrescriptionmService prescriptionServicem;
	@Override
	@Transactional
	public void examinem(Taskm taskm, List<Prescriptionm> list) {
		// TODO Auto-generated method stub
		taskm.setStatus(2);
		taskmService.update(taskm);
		HashSet<Integer> set = new HashSet<>();
		for (Prescriptionm prescriptionm : list) {
			set.add(prescriptionm.getDrugstoreId());
			prescriptionm.setTaskId(Integer.parseInt(taskm.getId().toString()));
			prescriptionm.setStatus(0);
			prescriptionServicem.save(prescriptionm);
		}
		User user = userDao.getById(Long.parseLong(taskm.getHerdsmanId().toString()));
		JpushClientUtil.sendToRegistrationId(user.getRegsId(), "通知", "你的诊断已完成，请查看药品", "1", "1");
		for (Integer integer : set) {
			System.out.println(integer);
			User user2 = userDao.getById(Long.parseLong(integer.toString()));
			JpushClientUtil.sendToRegistrationId(user2.getRegsId(), "通知", "你有新的药品订单", "1", "1");
		}
		
	}

	@Override
	public void examine(Task task, List<Prescription> list) {
		// TODO Auto-generated method stub
		task.setStatus(2);
		taskService.update(task);
		HashSet<Integer> set = new HashSet<>();
		for (Prescription prescription : list) {
			set.add(prescription.getDrugstoreId());
			prescription.setTaskId(Integer.parseInt(task.getId().toString()));
			prescription.setStatus(0);
			prescriptionService.save(prescription);
		}
		
		User user = userDao.getById(Long.parseLong(task.getHerdsmanId().toString()));
		JpushClientUtil.sendToRegistrationId(user.getRegsId(), "通知", "你的诊断已完成，请查看药品", "1", "1");
		for (Integer integer : set) {
			System.out.println(integer);
			User user2 = userDao.getById(Long.parseLong(integer.toString()));
			JpushClientUtil.sendToRegistrationId(user2.getRegsId(), "通知", "你有新的药品订单", "1", "1");
		}
	}

	@Autowired
	private UserService userService;
	/**
	 * @author: 孙少辉
	 * @data: 2018年4月26日
	 * @param phone
	 * @return
	 * @see com.orhonit.admin.server.sys.service.ExpertinfoService#CreateByPhone(java.lang.String)
	 * @Description: 专家根据牧民电话号添加病例  汉语
	 */
	@Override
	public ResponseEntity<?> CreateByPhone(String phone) {
		// TODO Auto-generated method stub
		try {
			User user = userService.getUser(phone);
			Task task = new Task();
			task.setExpertId(Integer.parseInt(UserUtil.getCurrentUser().getId().toString()));
			task.setHerdsmanId(Integer.parseInt(user.getId().toString()));
			task.setCreateTime(new Date());
			taskService.save(task);
			return ResponseEntity.ok(task);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	/**
	 * @author: 孙少辉
	 * @data: 2018年4月26日
	 * @param phone
	 * @return  
	 * @Description: 专家根据牧民电话号添加病例 蒙语 
	 */
	@Override
	public ResponseEntity<?> CreatemByPhone(String phone) {
		// TODO Auto-generated method stub
		try {
			User user = userService.getUser(phone);
			Taskm taskm = new Taskm();
			taskm.setExpertId(Integer.parseInt(UserUtil.getCurrentUser().getId().toString()));
			taskm.setHerdsmanId(Integer.parseInt(user.getId().toString()));
			taskm.setCreateTime(new Date());
			taskmService.save(taskm);
			return ResponseEntity.ok(taskm);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	 
}
