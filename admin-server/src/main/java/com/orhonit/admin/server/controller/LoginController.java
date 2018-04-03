package com.orhonit.admin.server.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.annotation.LogAnnotation;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.utils.UserUtil;
import com.orhonit.admin.server.token.Token;
import com.orhonit.admin.server.token.TokenManager;

import io.swagger.annotations.ApiOperation;

/**
 * 登陆相关接口
 * 
 * @author caodw
 *
 */
@RestController
@RequestMapping
public class LoginController {

	@Autowired
	private TokenManager tokenManager;

	@LogAnnotation
	@ApiOperation(value = "web端登陆")
	@PostMapping("/sys/login")
	public void login(String username, String password) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		SecurityUtils.getSubject().login(usernamePasswordToken);
	}

	@LogAnnotation
	@ApiOperation(value = "Restful方式登陆,前后端分离时登录接口")
	@PostMapping("/sys/login/restful")
	public Token restfulLogin(String username, String password) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		SecurityUtils.getSubject().login(usernamePasswordToken);
		User user = UserUtil.getCurrentUser();
		System.err.println(user);
		return tokenManager.saveToken(usernamePasswordToken);
	}
	@ApiOperation(value = "当前登录用户")
	@GetMapping("/sys/login")
	private User getLoginInfo() {
		return UserUtil.getCurrentUser();
	}

}
