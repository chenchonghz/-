package com.orhonit.admin.server.sys.service.impl;

import java.util.Date;
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
import com.orhonit.admin.server.sys.dao.DrugstoreinfoDao;
import com.orhonit.admin.server.sys.model.Drug;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import com.orhonit.admin.server.sys.model.Newherdsman;
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.service.DrugstoreinfoService;
import com.orhonit.admin.server.sys.service.NewherdsmanService;
import com.orhonit.admin.server.sys.service.TaskService;
import com.orhonit.admin.server.sys.service.TaskmService;
import com.orhonit.admin.server.sys.utils.UserUtil;

@Service
public class DrugstoreinfoServiceImpl implements DrugstoreinfoService {
	
	 @Autowired
	 private DrugstoreinfoDao drugstoreinfoDao;

	@Override
	public void save(Drugstoreinfo drugstoreinfo) {
		// TODO Auto-generated method stub
		drugstoreinfoDao.save(drugstoreinfo);
	}

	@Override
	public Drugstoreinfo getById(Long id) {
		// TODO Auto-generated method stub
		return drugstoreinfoDao.getById(id);
	}

	@Override
	public Drugstoreinfo getByUid(int id) {
		// TODO Auto-generated method stub
		return drugstoreinfoDao.getByUid(id);
	}

	@Override
	public List<Drugstoreinfo> getByStatus(int id) {
		// TODO Auto-generated method stub
		return drugstoreinfoDao.getByStatus(id);
	}

	@Override
	@Transactional
	public int getDrugstoreUid(Long id) {
		// TODO Auto-generated method stub
		Drugstoreinfo drugstore=drugstoreinfoDao.ById(id);
    	drugstore.setStatus(1);
    	return drugstoreinfoDao.update(drugstore);
	}

	@Override
	@Transactional
	public int getDrugstoreFailUid(Long id,String pass) {
		// TODO Auto-generated method stub
		Drugstoreinfo drugstore=drugstoreinfoDao.ById(id);
    	drugstore.setStatus(2);
    	drugstore.setReason(pass);
    	return drugstoreinfoDao.update(drugstore);
	}

	@Override
	public void update(Drugstoreinfo drugstoreinfo) {
		// TODO Auto-generated method stub
		drugstoreinfoDao.update(drugstoreinfo);
	}

	@Override
	public TableResponse<Drugstoreinfo> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Drugstoreinfo> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return drugstoreinfoDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Drugstoreinfo>() {

            @Override
            public List<Drugstoreinfo> list(TableRequest request) {
                return drugstoreinfoDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		drugstoreinfoDao.delete(id);
	}

	@Override
	public List<Drugstoreinfo> ten(Long start) {
		// TODO Auto-generated method stub
		return drugstoreinfoDao.ten(start);
	}

	/**
	 * @author: 孙少辉
	 * @data: 2018年4月24日
	 * @param parseInt
	 * @return
	 * @see com.orhonit.admin.server.sys.service.DrugstoreinfoService#AppGet(int)
	 * @Description: App端拿到药店个人信息 
	 */
	@Override
	public ResponseEntity<?> AppGet(int parseInt) {
		// TODO Auto-generated method stub
		Drugstoreinfo drugstoreinfo = drugstoreinfoDao.getByUid(parseInt);
		if(drugstoreinfo !=null){
			return ResponseEntity.ok(drugstoreinfo);
		}else{
			return ResponseEntity.status(401).body("错误");
		}
	}

	/**
	 * @author: 孙少辉
	 * @data: 2018年4月24日
	 * @param drugstoreinfo
	 * @return
	 * @see com.orhonit.admin.server.sys.service.DrugstoreinfoService#AppSave(com.orhonit.admin.server.sys.model.Drugstoreinfo)
	 * @Description: App端保存药店个人信息 
	 */
	@Override
	public ResponseEntity<?> AppSave(Drugstoreinfo drugstoreinfo) {
		// TODO Auto-generated method stub
		try {
			drugstoreinfo.setUid(Integer.parseInt(UserUtil.getCurrentUser().getId().toString()));
			drugstoreinfo.setCreateTime(new Date());
			drugstoreinfo.setUpdateTime(new Date());
			drugstoreinfo.setStatus(0);
			drugstoreinfoDao.save(drugstoreinfo);
			return ResponseEntity.ok(null);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> AppUpdate(Drugstoreinfo drugstoreinfo) {
		// TODO Auto-generated method stub
		try {
			drugstoreinfo.setUpdateTime(new Date());
			drugstoreinfo.setStatus(0);
			drugstoreinfoDao.AppUpdate(drugstoreinfo);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body(null);
		}
	}
	
	@Autowired
	private TaskmService taskmService;
	@Autowired
	private NewherdsmanService newherdsmanService;
	/**
	 * @author: 孙少辉
	 * @data: 2018年4月26日
	 * @param id
	 * @return 
	 * @see com.orhonit.admin.server.sys.service.DrugstoreinfoService#getByTaskIdM(java.lang.Integer)
	 * @Description: 根据诊断id和地区表对比拿到牧民附近的药店 
	 */
	@Override
	public ResponseEntity<?> getByTaskIdM(Integer id) {
		// TODO Auto-generated method stub
		try {
			Taskm taskm = taskmService.getById(Long.parseLong(id.toString()));
			Newherdsman newherdsman = newherdsmanService.getByUid(taskm.getHerdsmanId());
			System.out.println(newherdsman);
			List<Drugstoreinfo> list = drugstoreinfoDao.selectByNewHerdsManArea(newherdsman.getArea());
			for (Drugstoreinfo drugstoreinfo : list) {
				System.err.println(drugstoreinfo);
			}
			if(list.size()<10){
				List<Drugstoreinfo> list2= drugstoreinfoDao.selectByNewHerdsManCity(newherdsman.getCity(),newherdsman.getArea());
				int size =10 - list.size();
				for (int i = 0; i < size; i++) {
					if(i<list2.size()){
						list.add(list2.get(i));
					}
				}
			}
			return ResponseEntity.ok(list);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}
	@Autowired
	private TaskService taskService;
	/**
	 * @author: 孙少辉
	 * @data: 2018年4月26日
	 * @param id
	 * @return
	 * @see com.orhonit.admin.server.sys.service.DrugstoreinfoService#getByTaskId(java.lang.Integer)
	 * @Description: 根据诊断id和地区表对比拿到牧民附近的药店 汉语  
	 */
	@Override
	public ResponseEntity<?> getByTaskId(Integer id) {
		// TODO Auto-generated method stub
		try {
			Task task = taskService.getById(Long.parseLong(id.toString()));
			System.out.println(task.getHerdsmanId());
			Newherdsman newherdsman = newherdsmanService.getByUid(task.getHerdsmanId());
			System.out.println(newherdsman);
			List<Drugstoreinfo> list = drugstoreinfoDao.selectByNewHerdsManAreaMeng(newherdsman.getAreaMeng());
			for (Drugstoreinfo drugstoreinfo : list) {
				System.err.println(drugstoreinfo);
			}
			if(list.size()<10){
				List<Drugstoreinfo> list2= drugstoreinfoDao.selectByNewHerdsManCityMeng(newherdsman.getCityMeng(),newherdsman.getAreaMeng());
				int size =10 - list.size();
				for (int i = 0; i < size; i++) {
					if(i<list2.size()){
						list.add(list2.get(i));
					}
				}
			}
			return ResponseEntity.ok(list);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> getDrug(Integer id) {
		// TODO Auto-generated method stub
		try {
			List<Drug> list = drugstoreinfoDao.getDrug(id);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}
}
