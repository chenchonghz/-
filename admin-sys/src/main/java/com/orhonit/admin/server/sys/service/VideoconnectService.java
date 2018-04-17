package com.orhonit.admin.server.sys.service;

import java.util.List;
import java.util.Map;

import com.orhonit.admin.server.sys.model.Videoconnect;

public interface VideoconnectService {

	void save(Videoconnect videoconnect);

	Videoconnect getById(Long id);

	void update(Videoconnect videoconnect);

	int count(Map<String, Object> params);

	List<Videoconnect> list(Map<String, Object> params, Integer start, Integer length);

	void delete(Long id);

	Videoconnect saveVc(int eid, int type);

	void returnVc(String hid, String ifOrNot);

	void stopVc(String id);


}
