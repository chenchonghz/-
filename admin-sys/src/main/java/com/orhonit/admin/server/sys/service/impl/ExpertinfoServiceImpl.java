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
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.model.Videoconnect;
import com.orhonit.admin.server.sys.service.ExpertinfoService;
import com.orhonit.admin.server.sys.service.TaskService;
import com.orhonit.admin.server.sys.service.TaskmService;
import com.orhonit.admin.server.sys.service.VideoconnectService;
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
	 
}
