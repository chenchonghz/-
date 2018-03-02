package com.orhonit.admin.server.online.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.orhonit.admin.server.online.handler.UserLoginSocketHandler;
import com.orhonit.admin.server.online.handler.UserOnlineSocketHandler;
import com.orhonit.admin.server.online.interceptor.UserOnlineInterceptor;

/**
 * websocket配置
 * 
 * @author caodw
 *
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private UserLoginSocketHandler userLoginSocketHandler;
	@Autowired
	private UserOnlineSocketHandler userOnlineSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		UserOnlineInterceptor userOnlineInterceptor = new UserOnlineInterceptor();

		registry.addHandler(userLoginSocketHandler, "/ws/users/login").addInterceptors(userOnlineInterceptor)
				.setAllowedOrigins("*");
		registry.addHandler(userLoginSocketHandler, "/sockjs/ws/users/login").setAllowedOrigins("*").withSockJS();

		registry.addHandler(userOnlineSocketHandler, "/ws/users/online").addInterceptors(userOnlineInterceptor)
				.setAllowedOrigins("*");
		registry.addHandler(userOnlineSocketHandler, "/sockjs/ws/users/online").setAllowedOrigins("*").withSockJS();
	}

}
