package com.orhonit.admin.server.online.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.orhonit.admin.server.common.constants.EventType;
import com.orhonit.admin.server.common.constants.UserConstants;
import com.orhonit.admin.server.common.event.AdminEvent;
import com.orhonit.admin.server.common.utils.JsonUtil;
import com.orhonit.admin.server.online.constants.MsgType;
import com.orhonit.admin.server.online.dto.LoginUserDto;
import com.orhonit.admin.server.online.dto.NoticeDto;
import com.orhonit.admin.server.online.dto.ReturnVcDto;
import com.orhonit.admin.server.online.dto.StopVcDto;
import com.orhonit.admin.server.online.dto.VideoDto;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dto.ReturnVc;
import com.orhonit.admin.server.sys.dto.StopVc;
import com.orhonit.admin.server.sys.model.Articles;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.model.Videoconnect;
import com.orhonit.admin.server.sys.utils.UserUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 登陆相关推送
 * 
 * @author caodw
 *
 *
 */
@Slf4j(topic = "adminLogger")
@Component
public class UserLoginSocketHandler implements WebSocketHandler, ApplicationListener<AdminEvent> {
	@Autowired
	private ExpertinfoDao expertinfoDao;
	
	
	/**
	 * 登录用户打开所有页面的连接
	 */
	public static ConcurrentMap<Long, Map<String, WebSocketSession>> sessions = new ConcurrentHashMap<>();
	/**
	 * 在线用户
	 */
	public static ConcurrentMap<Long, User> onlineUsers = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		User user = (User) session.getAttributes().get(UserConstants.ONLINE_USER);
		Long id = user.getId();
		String username = user.getUsername();
		synchronized (username.intern()) {
			String onlineUserKey = (String) session.getAttributes().get(UserConstants.ONLINE_USER_KEY);

			Map<String, WebSocketSession> map = null;
			if (sessions.containsKey(id)) {
				map = sessions.get(id);
			} else {
				map = new HashMap<>();
				sessions.put(id, map);
			}
			map.put(onlineUserKey, session);

			addOnlineUser(user);

			LoginUserDto loginUserDto = new LoginUserDto();
			loginUserDto.setType(MsgType.LOGIN_USER.name());
			loginUserDto.setUser(user);

			session.sendMessage(new TextMessage(JsonUtil.toJson(loginUserDto)));
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		removeSession(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		removeSession(session);
	}

	@Autowired
	private UserOnlineSocketHandler userOnlineSocketHandler;

	private void removeSession(WebSocketSession session) throws IOException {
		User user = (User) session.getAttributes().get(UserConstants.ONLINE_USER);
		Long id = user.getId();
		String username = user.getUsername();
		synchronized (username.intern()) {
			if (session.isOpen()) {
				session.close();
			}

			String onlineUserKey = (String) session.getAttributes().get(UserConstants.ONLINE_USER_KEY);
			Map<String, WebSocketSession> map = sessions.get(id);
			if (CollectionUtils.isEmpty(map)) {
				return;
			}
			map.remove(onlineUserKey);
			log.debug("移除连接{}:{}", user.getNickname(), onlineUserKey);
			if(user.getType() == 1){
				expertinfoDao.updateState(Integer.parseInt(user.getId().toString()), 3);
			}

			if (map.isEmpty()) {
				sessions.remove(id);
				onlineUsers.remove(id);
				userOnlineSocketHandler.sendToAll();
				log.debug("移除在线用户:{}", user.getNickname());
			}

		}
	}

	/**
	 * 添加在线用户
	 * 
	 * @param user
	 */
	private void addOnlineUser(User user) {
		Long id = user.getId();
		User oldUser = onlineUsers.putIfAbsent(id, user);
		if (oldUser == null) {
			log.debug("添加在线用户：{}", user.getUsername());
			if(user.getType() == 3){
				expertinfoDao.updateState(Integer.parseInt(user.getId().toString()),1);
			}
			userOnlineSocketHandler.sendToAll();
		}
	
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	@Override
	public void onApplicationEvent(AdminEvent adminEvent) {
		if (adminEvent.getEventType() == EventType.USER_CHANGE) {
			User user = (User) adminEvent.getSource();
			log.debug("用户{}信息变动", user.getUsername());

			Map<String, WebSocketSession> map = sessions.get(user.getId());

			LoginUserDto loginUserDto = new LoginUserDto();
			loginUserDto.setType(MsgType.LOGIN_USER.name());
			loginUserDto.setUser(user);
			String msg = JsonUtil.toJson(loginUserDto);

			sendMsg(map, msg);
		} else if (EventType.NEW_NOTICE == adminEvent.getEventType()) {
			Articles articles = (Articles) adminEvent.getSource();
			User user = UserUtil.getCurrentUser();

			NoticeDto dto = new NoticeDto();
			dto.setType(EventType.NEW_NOTICE.name());
			dto.setArticles(articles);

			String msg = JsonUtil.toJson(dto);

			sessions.forEach((k, v) -> {
				if (!k.equals(user.getId())) {
					sendMsg(v, msg);
				}
			});

		}else if(EventType.NEW_VIDEO == adminEvent.getEventType()){
			Videoconnect videoconnect = (Videoconnect) adminEvent.getSource();
			VideoDto videoDto = new VideoDto();
			videoDto.setType(EventType.NEW_VIDEO.name());
			videoDto.setVideoconnect(videoconnect);
			String msg = JsonUtil.toJson(videoDto);
			sessions.forEach((k, v) -> {
				if (k==Long.parseLong(videoconnect.getEid().toString())) {
					sendMsg(v, msg);
				}
			});
			
		}else if(EventType.VIDEO_RETURN == adminEvent.getEventType()){
			ReturnVc returnVc = (ReturnVc) adminEvent.getSource();
			ReturnVcDto returnVcDto = new ReturnVcDto();
			returnVcDto.setType(EventType.VIDEO_RETURN.name());
			returnVcDto.setReturnVc(returnVc);
			String msg = JsonUtil.toJson(returnVcDto);
			sessions.forEach((k, v) -> {
				if (k == Long.parseLong(returnVc.getHid().toString())) {
					sendMsg(v, msg);
				}
			});
		}else if(EventType.VIDEO_STOP_E == adminEvent.getEventType()){
			StopVc stopVc = (StopVc) adminEvent.getSource();
			StopVcDto stopVcDto = new StopVcDto();
			stopVcDto.setType(EventType.VIDEO_STOP_E.name());
			stopVcDto.setStopVc(stopVc);
			String msg = JsonUtil.toJson(stopVcDto);
			sessions.forEach((k, v) -> {
				if (k == Long.parseLong(stopVc.getId().toString())) {
					sendMsg(v, msg);
				}
			});
		}else if(EventType.VIDEO_STOP_H == adminEvent.getEventType()){
			StopVc stopVc = (StopVc) adminEvent.getSource();
			StopVcDto stopVcDto = new StopVcDto();
			stopVcDto.setType(EventType.VIDEO_STOP_H.name());
			stopVcDto.setStopVc(stopVc);
			String msg = JsonUtil.toJson(stopVcDto);
			sessions.forEach((k, v) -> {
				if (k == Long.parseLong(stopVc.getId().toString())) {
					sendMsg(v, msg);
				}
			});
		}
	}

	private void sendMsg(Map<String, WebSocketSession> map, String msg) {
		if (!CollectionUtils.isEmpty(map)) {
			map.values().forEach(m -> {
				try {
					if (m.isOpen()) {
						m.sendMessage(new TextMessage(msg));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

}
