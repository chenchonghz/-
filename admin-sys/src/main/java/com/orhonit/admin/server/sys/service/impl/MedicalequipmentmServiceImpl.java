package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.MedicalequipmentmDao;
import com.orhonit.admin.server.sys.model.Medicalequipmentm;
import com.orhonit.admin.server.sys.service.MedicalequipmentmService;

@Service
public class MedicalequipmentmServiceImpl implements MedicalequipmentmService {
	 @Autowired
	 private MedicalequipmentmDao medicalequipmentmDao;

	@Override
	public void save(Medicalequipmentm medicalequipmentm) {
		// TODO Auto-generated method stub
		medicalequipmentmDao.save(medicalequipmentm);
	}

	@Override
	public Medicalequipmentm getById(Long id) {
		// TODO Auto-generated method stub
		return medicalequipmentmDao.getById(id);
	}

	@Override
	public void update(Medicalequipmentm medicalequipmentm) {
		// TODO Auto-generated method stub
		medicalequipmentmDao.update(medicalequipmentm);
	}

	@Override
	public TableResponse<Medicalequipmentm> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<Medicalequipmentm> builder().countHandler(new CountHandler() {

	            @Override
	            public int count(TableRequest request) {
	                return medicalequipmentmDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<Medicalequipmentm>() {

	            @Override
	            public List<Medicalequipmentm> list(TableRequest request) {
	                return medicalequipmentmDao.list(request.getParams(), request.getStart(), request.getLength());
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		medicalequipmentmDao.delete(id);
	}

	@Override
	public List<Medicalequipmentm> all() {
		// TODO Auto-generated method stub
		return medicalequipmentmDao.all();
	}
	 
}
