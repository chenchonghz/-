package com.orhonit.admin.server.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orhonit.admin.server.common.constants.EventType;
import com.orhonit.admin.server.common.event.AdminEvent;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.VideoconnectDao;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.model.Videoconnect;
import com.orhonit.admin.server.sys.service.VideoconnectService;
import com.orhonit.admin.server.sys.utils.UserUtil;

@Service
public class VideoconnectServiceImpl implements VideoconnectService {

	@Autowired
	private VideoconnectDao videoconnectDao;
	
	@Autowired
	private ExpertinfoDao expertinfoDao;
	 
	@Value("${videoconnect.path}")
	private String videoconnectPath;


	@Override
	public void save(Videoconnect videoconnect) {
		// TODO Auto-generated method stub
		videoconnectDao.save(videoconnect);
	}

	@Override
	public Videoconnect getById(Long id) {
		// TODO Auto-generated method stub
		return videoconnectDao.getById(id);
	}

	@Override
	public void update(Videoconnect videoconnect) {
		// TODO Auto-generated method stub
		videoconnectDao.update(videoconnect);
	}

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return videoconnectDao.count(params);
	}

	@Override
	public List<Videoconnect> list(Map<String, Object> params, Integer start, Integer length) {
		// TODO Auto-generated method stub
		return videoconnectDao.list(params, start, length);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		videoconnectDao.delete(id);
	}

	@Override
	@Transactional
	public Videoconnect saveVc(int eid) {
		// TODO Auto-generated method stub
		Expertinfo expertinfo = this.expertinfoDao.ByUid(eid);
    	if(expertinfo.getState() == 1) {
    		Videoconnect videoConnect =new Videoconnect();
        	User currentUser = UserUtil.getCurrentUser();
            videoConnect.setHid(Integer.parseInt(currentUser.getId().toString()));
            videoConnect.setEid(eid);
            videoConnect.setUrl(videoconnectPath);
            int round = (int) (Math.random() * 1000000);
            videoConnect.setRoomid(round);
            videoConnect.setTime(new Date());
            videoconnectDao.save(videoConnect);
            expertinfo.setState(2);
            expertinfoDao.update(expertinfo);
            sendMsg(videoConnect);
            return videoConnect;
    	}else {
    		return null;
    	}
	}

	@Autowired
	private ApplicationContext applicationContext;

	private void sendMsg(Videoconnect videoConnect) {
		applicationContext.publishEvent(new AdminEvent(videoConnect, EventType.NEW_VIDEO));
	}

	
	
}
