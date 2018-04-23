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
import com.orhonit.admin.server.sys.dto.ReturnVc;
import com.orhonit.admin.server.sys.dto.StopVc;
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
	public Videoconnect saveVc(int eid,int type) {
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
            videoConnect.setType(type);
            videoconnectDao.save(videoConnect);
            expertinfo.setState(2);
            expertinfoDao.update(expertinfo);
            sendMsg(videoConnect);
            return videoConnect;
    	}else {
    		return null;
    	}
	}

	
	
	/**
	 * @author: 孙少辉
	 * @data: 2018年4月16日
	 * @param hid
	 * @param type
	 * @param ifOrNot
	 * @see com.orhonit.admin.server.sys.service.VideoconnectService#returnVc(java.lang.String, java.lang.String, java.lang.String)
	 * @Description: 专家回复牧民的视频通话请求 
	 */
	@Override
	public void returnVc(String hid, String ifOrNot) { //ifOrNot:1接受 0拒绝
		// TODO Auto-generated method stub
		/*
		 * 发送给牧民的视频通话回执
		 */
		if(Integer.parseInt(ifOrNot) == 2){
			Long id = UserUtil.getCurrentUser().getId();
			expertinfoDao.updateState(id.intValue(), 1);
		}
		ReturnVc returnVc = new ReturnVc();
		returnVc.setHid(Integer.parseInt(hid));
		returnVc.setIfOrNot(Integer.parseInt(ifOrNot));
		this.sendReturnMsg(returnVc);
	}
	
	/**
	 * @author: 孙少辉
	 * @data: 2018年4月17日
	 * @param id
	 * @see com.orhonit.admin.server.sys.service.VideoconnectService#stopVc(java.lang.String)
	 * @Description: TODO 
	 */
	@Override
	public void stopVc(String id) {
		// TODO Auto-generated method stub
		int Uid = Integer.parseInt(UserUtil.getCurrentUser().getId().toString());
		Videoconnect videoconnect = videoconnectDao.getById(Long.parseLong(id));
		expertinfoDao.updateState(videoconnect.getEid(), 1);//专家在线状态改变为1
		expertinfoDao.updatevideoDiagnosis(videoconnect.getEid());//专家视频诊断数量加1
		StopVc stopVc = new StopVc();
		stopVc.setStop(1);
		if(Uid == videoconnect.getEid()){
			stopVc.setId(videoconnect.getHid());
			this.sendStopMsgH(stopVc);
		}else{
			stopVc.setId(videoconnect.getEid());
			this.sendStopMsgE(stopVc);
		}
		
	}

	private void sendStopMsgE(StopVc stopVc) {
		// TODO Auto-generated method stub
		applicationContext.publishEvent(new AdminEvent(stopVc, EventType.VIDEO_STOP_E));
	}

	private void sendStopMsgH(StopVc stopVc) {
		// TODO Auto-generated method stub
		applicationContext.publishEvent(new AdminEvent(stopVc, EventType.VIDEO_STOP_H));
	}

	@Autowired
	private ApplicationContext applicationContext;
	private void sendMsg(Videoconnect videoConnect) {
		applicationContext.publishEvent(new AdminEvent(videoConnect, EventType.NEW_VIDEO));
	}

	public void sendReturnMsg(ReturnVc returnVc) {
		// TODO Auto-generated method stub
		applicationContext.publishEvent(new AdminEvent(returnVc, EventType.VIDEO_RETURN));
	}



	
	
}
