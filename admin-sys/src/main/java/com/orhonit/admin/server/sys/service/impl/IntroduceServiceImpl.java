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
import com.orhonit.admin.server.sys.dao.IntroduceDao;
import com.orhonit.admin.server.sys.model.Introduce;
import com.orhonit.admin.server.sys.service.IntroduceService;
@Service
public class IntroduceServiceImpl implements IntroduceService {
	@Autowired
    private IntroduceDao introduceDao;

	@Override
	public void save(Introduce introduce) {
		// TODO Auto-generated method stub
		introduceDao.save(introduce);
	}

	@Override
	public Introduce getById(Long id) {
		// TODO Auto-generated method stub
		return introduceDao.getById(id);
	}

	@Override
	public void update(Introduce introduce) {
		// TODO Auto-generated method stub
		introduceDao.update(introduce);
	}

	@Override
	public TableResponse<Introduce> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Introduce> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return introduceDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Introduce>() {

            @Override
            public List<Introduce> list(TableRequest request) {
                return introduceDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		introduceDao.delete(id);
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		try {
			List<Introduce> list = introduceDao.getAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}
}
