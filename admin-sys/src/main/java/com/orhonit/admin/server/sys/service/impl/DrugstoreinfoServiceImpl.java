package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.DrugstoreinfoDao;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import com.orhonit.admin.server.sys.service.DrugstoreinfoService;

@Service
public class DrugstoreinfoServiceImpl implements DrugstoreinfoService {
	
	 @Autowired
	 private DrugstoreinfoDao drugstoreinfoDao;

	@Override
	public void save(Drugstoreinfo drugstoreinfo) {
		// TODO Auto-generated method stub
		drugstoreinfoDao.save(drugstoreinfo);
	}

	@Override
	public Drugstoreinfo getById(Long id) {
		// TODO Auto-generated method stub
		return drugstoreinfoDao.getById(id);
	}

	@Override
	public Drugstoreinfo getByUid(int id) {
		// TODO Auto-generated method stub
		return drugstoreinfoDao.getByUid(id);
	}

	@Override
	public List<Drugstoreinfo> getByStatus(int id) {
		// TODO Auto-generated method stub
		return drugstoreinfoDao.getByStatus(id);
	}

	@Override
	@Transactional
	public int getDrugstoreUid(Long id) {
		// TODO Auto-generated method stub
		Drugstoreinfo drugstore=drugstoreinfoDao.ById(id);
    	drugstore.setStatus(1);
    	return drugstoreinfoDao.update(drugstore);
	}

	@Override
	@Transactional
	public int getDrugstoreFailUid(Long id) {
		// TODO Auto-generated method stub
		Drugstoreinfo drugstore=drugstoreinfoDao.ById(id);
    	drugstore.setStatus(2);
    	return drugstoreinfoDao.update(drugstore);
	}

	@Override
	public void update(Drugstoreinfo drugstoreinfo) {
		// TODO Auto-generated method stub
		drugstoreinfoDao.update(drugstoreinfo);
	}

	@Override
	public TableResponse<Drugstoreinfo> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Drugstoreinfo> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return drugstoreinfoDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Drugstoreinfo>() {

            @Override
            public List<Drugstoreinfo> list(TableRequest request) {
                return drugstoreinfoDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		drugstoreinfoDao.delete(id);
	}
}
