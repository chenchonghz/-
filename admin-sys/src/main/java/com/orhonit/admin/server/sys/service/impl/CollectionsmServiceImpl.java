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
import com.orhonit.admin.server.sys.dao.CollectionsmDao;
import com.orhonit.admin.server.sys.model.Collectionsm;
import com.orhonit.admin.server.sys.service.CollectionsmService;
import com.orhonit.admin.server.sys.utils.UserUtil;
@Service
public class CollectionsmServiceImpl implements CollectionsmService {
	@Autowired
    private CollectionsmDao collectionsmDao;

	@Override
	public void save(Collectionsm collectionsm) {
		// TODO Auto-generated method stub
		collectionsmDao.save(collectionsm);
	}

	@Override
	public Collectionsm getById(Long id) {
		// TODO Auto-generated method stub
		return collectionsmDao.getById(id);
	}

	@Override
	public void update(Collectionsm collectionsm) {
		// TODO Auto-generated method stub
		collectionsmDao.update(collectionsm);
	}

	@Override
	public TableResponse<Collectionsm> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Collectionsm> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return collectionsmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Collectionsm>() {

            @Override
            public List<Collectionsm> list(TableRequest request) {
                return collectionsmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		collectionsmDao.delete(id);
	}

	@Override
	public ResponseEntity<?> appAdd(Integer cateId, Integer chlidrenId) {
		try {
			Long id = UserUtil.getCurrentUser().getId();
			Collectionsm collections = new Collectionsm();
			collections.setCateId(cateId);
			collections.setChlidrenId(chlidrenId);
			collections.setHerdsmanId(Integer.parseInt(id.toString()));
			collectionsmDao.save(collections);
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
			List<Collectionsm> list = collectionsmDao.Applook(id,cateId);
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
			collectionsmDao.delete(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}
}
