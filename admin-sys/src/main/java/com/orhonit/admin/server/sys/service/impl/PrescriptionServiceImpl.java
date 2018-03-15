package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.PrescriptionDao;
import com.orhonit.admin.server.sys.model.Prescription;
import com.orhonit.admin.server.sys.service.PrescriptionService;
@Service
public class PrescriptionServiceImpl implements PrescriptionService {
	@Autowired
    private PrescriptionDao prescriptionDao;

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		prescriptionDao.delete(id);
	}

	@Override
	public TableResponse<Prescription> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<Prescription> builder().countHandler(new CountHandler() {
	            @Override
	            public int count(TableRequest request) {
	                return prescriptionDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<Prescription>() {

	            @Override
	            public List<Prescription> list(TableRequest request) {
	                return prescriptionDao.list(request.getParams(), request.getStart(), request.getLength());
	            }
	        }).build().handle(request);
	}

	@Override
	public void update(Prescription prescription) {
		// TODO Auto-generated method stub
		prescriptionDao.update(prescription);
	}

	@Override
	public Prescription getById(Long id) {
		// TODO Auto-generated method stub
		return prescriptionDao.getById(id);
	}

	@Override
	public void save(Prescription prescription) {
		// TODO Auto-generated method stub
		prescriptionDao.save(prescription);
	}
	
}
