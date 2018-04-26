package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.PrescriptionmDao;
import com.orhonit.admin.server.sys.model.Prescriptionm;
import com.orhonit.admin.server.sys.service.PrescriptionmService;
@Service
public class PrescriptionmServiceImpl implements PrescriptionmService {

	@Autowired
    private PrescriptionmDao prescriptionmDao;
	@Override
	public void save(Prescriptionm prescriptionm) {
		// TODO Auto-generated method stub
		prescriptionmDao.save(prescriptionm);
	}

	@Override
	public Prescriptionm getById(Long id) {
		// TODO Auto-generated method stub
		return prescriptionmDao.getById(id);
	}

	@Override
	public void update(Prescriptionm prescriptionm) {
		// TODO Auto-generated method stub
		prescriptionmDao.update(prescriptionm);
	}

	@Override
	public TableResponse<Prescriptionm> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Prescriptionm> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return prescriptionmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Prescriptionm>() {

            @Override
            public List<Prescriptionm> list(TableRequest request) {
                return prescriptionmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		prescriptionmDao.delete(id);
	}
	
}
