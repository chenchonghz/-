package com.orhonit.admin.server.sys.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orhonit.admin.server.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = -6525908145032868837L;

	private String username;
	private String password;
	@JsonIgnore
	private String salt;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private String telephone;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private Integer sex;
	private Integer status;
	private Integer type;
	private Integer typestatus;
	public interface Status {
		int DISABLED = 0;
		int VALID = 1;;
		int LOCKED = 2;
	}
	private Integer xinxi;

}
