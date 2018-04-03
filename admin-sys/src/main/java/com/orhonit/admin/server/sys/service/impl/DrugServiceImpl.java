package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.DrugDao;
import com.orhonit.admin.server.sys.model.Drug;
import com.orhonit.admin.server.sys.service.DrugService;
@Service
public class DrugServiceImpl implements DrugService {
	@Autowired
    private DrugDao drugDao;

	@Override
	public void save(Drug drug) {
		// TODO Auto-generated method stub
		drugDao.save(drug);
	}

	@Override
	public Drug getById(Long id) {
		// TODO Auto-generated method stub
		return drugDao.getById(id);
	}

	@Override
	public void update(Drug drug) {
		// TODO Auto-generated method stub
		drugDao.update(drug);
	}

	@Override
	public TableResponse<Drug> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Drug> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return drugDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Drug>() {

            @Override
            public List<Drug> list(TableRequest request) {
                return drugDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		drugDao.delete(id);
	}

	@Override
	public List<Drug> getByUid(Long uid) {
		// TODO Auto-generated method stub
		return drugDao.getByUid(uid);
	}
}
