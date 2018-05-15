package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.CollectionsDao;
import com.orhonit.admin.server.sys.model.Collections;
import com.orhonit.admin.server.sys.service.CollectionsService;
import com.orhonit.admin.server.sys.utils.UserUtil;
@Service
public class CollectionsServiceImpl implements CollectionsService {
	@Autowired
    private CollectionsDao collectionsDao;

	@Override
	public void save(Collections collections) {
		// TODO Auto-generated method stub
		collectionsDao.save(collections);
	}

	@Override
	public Collections getById(Long id) {
		// TODO Auto-generated method stub
		return collectionsDao.getById(id);
	}

	@Override
	public void update(Collections collections) {
		// TODO Auto-generated method stub
		collectionsDao.update(collections);
	}

	@Override
	public TableResponse<Collections> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<Collections> builder().countHandler(new CountHandler() {

	            @Override
	            public int count(TableRequest request) {
	                return collectionsDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<Collections>() {

	            @Override
	            public List<Collections> list(TableRequest request) {
	                return collectionsDao.list(request.getParams(), request.getStart(), request.getLength());
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		collectionsDao.delete(id);
	}

	@Override
	public ResponseEntity<?> appAdd(Integer cateId, Integer chlidrenId) {
		// TODO Auto-generated method stub
		try {
			Long id = UserUtil.getCurrentUser().getId();
			Collections collections = new Collections();
			collections.setCateId(cateId);
			collections.setChlidrenId(chlidrenId);
			collections.setHerdsmanId(Integer.parseInt(id.toString()));
			collectionsDao.save(collections);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> Applook(Integer cateId) {
		// TODO Auto-generated method stub
		try {
			Long id = UserUtil.getCurrentUser().getId();
			List<Collections> list = collectionsDao.Applook(id,cateId);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body(null);
		}
	}

	@Override
	public ResponseEntity<?> AppDelete(Long id) {
		// TODO Auto-generated method stub
		try {
			collectionsDao.delete(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

}
