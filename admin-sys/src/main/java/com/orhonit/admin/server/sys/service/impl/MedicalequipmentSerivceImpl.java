package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.MedicalequipmentDao;
import com.orhonit.admin.server.sys.model.Medicalequipment;
import com.orhonit.admin.server.sys.service.MedicalequipmentSerivce;

@Service
public class MedicalequipmentSerivceImpl implements MedicalequipmentSerivce {

	@Autowired
	private MedicalequipmentDao medicalequipmentDao;
	
	@Override
	public void save(Medicalequipment medicalequipment) {
		// TODO Auto-generated method stub
		medicalequipmentDao.save(medicalequipment);
	}

	@Override
	public Medicalequipment getById(Long id) {
		// TODO Auto-generated method stub
		return medicalequipmentDao.getById(id);
	}

	@Override
	public void update(Medicalequipment medicalequipment) {
		// TODO Auto-generated method stub
		medicalequipmentDao.update(medicalequipment);
	}

	@Override
	public TableResponse<Medicalequipment> list(TableRequest request) {
		// TODO Auto-generated method stub
		 return TableRequestHandler.<Medicalequipment> builder().countHandler(new CountHandler() {
	            @Override
	            public int count(TableRequest request) {
	                return medicalequipmentDao.count(request.getParams());
	            }
	        }).listHandler(new ListHandler<Medicalequipment>() {
	            @Override
	            public List<Medicalequipment> list(TableRequest request) {
	                return medicalequipmentDao.list(request.getParams(), request.getStart(), request.getLength());
	            }
	        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		medicalequipmentDao.delete(id);
	}

}
