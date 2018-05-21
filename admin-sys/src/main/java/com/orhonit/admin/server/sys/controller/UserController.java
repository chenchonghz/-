package com.orhonit.admin.server.sys.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.annotation.LogAnnotation;
import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.UserDao;
import com.orhonit.admin.server.sys.dto.UserDto;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.service.UserService;
import com.orhonit.admin.server.sys.utils.UserUtil;
import com.orhonit.admin.server.sys.dao.DrugstoreinfoDao;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户相关接口
 * 
 * @author caodw
 *
 */
@Slf4j(topic = "adminLogger")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	 
	@Autowired
	private ExpertinfoDao expertinfoDao;
	    
	@Autowired
	private DrugstoreinfoDao drugstoreinfoDao;
	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存用户")
	@RequiresPermissions("sys:user:add")
	public User saveUser(@RequestBody UserDto userDto) {
		User u = userService.getUser(userDto.getUsername());
		if (u != null) {
			throw new IllegalArgumentException(userDto.getUsername() + "已存在");
		}

		return userService.saveUser(userDto);
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改用户")
	@RequiresPermissions("sys:user:add")
	public User updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

	@LogAnnotation
	@PutMapping(params = "headImgUrl")
	@ApiOperation(value = "修改头像")
	public void updateHeadImgUrl(String headImgUrl) {
		User user = UserUtil.getCurrentUser();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		userDto.setHeadImgUrl(headImgUrl);

		userService.updateUser(userDto);
		log.debug("{}修改了头像", user.getUsername());
	}

	

	@GetMapping
	@ApiOperation(value = "用户列表")
	@RequiresPermissions("sys:user:query")
	public TableResponse<User> listUsers(TableRequest request) {
		return TableRequestHandler.<User> builder().countHandler(new CountHandler() {

			@Override
			public int count(TableRequest request) {
				return userDao.count(request.getParams());
			}
		}).listHandler(new ListHandler<User>() {

			@Override
			public List<User> list(TableRequest request) {
				List<User> list = userDao.list(request.getParams(), request.getStart(), request.getLength());
				for(User user:list) {
					if(user.getType()==2) {
						Expertinfo expert= expertinfoDao.ById(user.getId());
						if(expert!=null) {
							user.setTypestatus(expert.getStatus());
            			}
					}else if(user.getType()==3) {
        				Drugstoreinfo drugstoreinfo=drugstoreinfoDao.ById(user.getId());
            			if(drugstoreinfo!=null) {
            				user.setTypestatus(drugstoreinfo.getStatus());
            			}
        			}
				}
				return list;
			}
		}).build().handle(request);
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/current")
	public User currentUser() {
		return UserUtil.getCurrentUser();
	}

	@ApiOperation(value = "根据用户id获取用户")
	@GetMapping("/{id}")
	@RequiresPermissions("sys:user:query")
	public User user(@PathVariable Long id) {
		return userDao.getById(id);
	}

}
