package com.orhonit.admin.server;

import com.orhonit.admin.server.sys.utils.JpushClientUtil;


public class JpushTest {
	public static void main(String[] args) {
		
		JpushClientUtil.sendToRegistrationId("1a0018970a8b56b70f1", "通知", "内容标题", "内容", "url");
	}
}
