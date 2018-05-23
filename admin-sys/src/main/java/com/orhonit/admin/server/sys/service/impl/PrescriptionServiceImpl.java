package com.orhonit.admin.server.sys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.DrugstoreinfoDao;
import com.orhonit.admin.server.sys.dao.PrescriptionDao;
import com.orhonit.admin.server.sys.dto.DrugPre2;
import com.orhonit.admin.server.sys.dto.PrescriptionDto;
import com.orhonit.admin.server.sys.dto.drugstoreDto;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import com.orhonit.admin.server.sys.model.Prescription;
import com.orhonit.admin.server.sys.service.PrescriptionService;
import com.orhonit.admin.server.sys.utils.UserUtil;
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
	@Autowired
	private DrugstoreinfoDao drugstoreinfoDao;
	@Override
	public ResponseEntity<?> getP(Long taskId) {
		// TODO Auto-generated method stub
		try {
			Set<Integer> set = new HashSet<>();
			List<PrescriptionDto> list = prescriptionDao.getP(taskId);
			for (PrescriptionDto prescriptionDto : list) {
				set.add(prescriptionDto.getDrugstoreId());
			}
			List<DrugPre2> drugPre2s = new ArrayList<>();
			for (Integer i : set) {
				DrugPre2 drugPre2 = new DrugPre2();
				Drugstoreinfo drugstoreinfo = drugstoreinfoDao.getByUid(i);
				drugPre2.setDrugstoreinfo(drugstoreinfo);
				ArrayList<PrescriptionDto> arrayList = new ArrayList<>();
				for (PrescriptionDto prescriptionDto : list) {
					if(prescriptionDto.getDrugstoreId() == drugstoreinfo.getUid()){
						arrayList.add(prescriptionDto);
					}
				}
				drugPre2.setPrescriptionDtos(arrayList);
				drugPre2s.add(drugPre2);
			}
			return ResponseEntity.ok(drugPre2s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> drugsGetList() {
		// TODO Auto-generated method stub
		try {
			Long userId = UserUtil.getCurrentUser().getId();
			List<drugstoreDto> list = prescriptionDao.drugsGetList(userId);
			for (drugstoreDto drugstoreDto : list) {
				List<Prescription> prescriptions= prescriptionDao.selectPre(drugstoreDto.getTaskId(),drugstoreDto.getDrugstoreId());
				drugstoreDto.setPrescriptions(prescriptions);
			}
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> updateStatus(Long taskId) {
		// TODO Auto-generated method stub
		try {
			Long userId = UserUtil.getCurrentUser().getId();
			prescriptionDao.updateStatus(taskId,userId);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body(null);
		}
	}
	
}
