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
import com.orhonit.admin.server.sys.dao.PrescriptionmDao;
import com.orhonit.admin.server.sys.dto.DrugPre;
import com.orhonit.admin.server.sys.dto.PrescriptionmDto;
import com.orhonit.admin.server.sys.dto.drugstoremDto;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import com.orhonit.admin.server.sys.model.Prescriptionm;
import com.orhonit.admin.server.sys.service.PrescriptionmService;
import com.orhonit.admin.server.sys.utils.UserUtil;
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
	@Autowired
	private DrugstoreinfoDao drugstoreinfoDao;

	@Override
	public ResponseEntity<?> getP(Long taskId) {
		// TODO Auto-generated method stub
		try {
			Set<Integer> set = new HashSet<>();
			List<PrescriptionmDto> list = prescriptionmDao.getP(taskId);
			for (PrescriptionmDto prescriptionmDto : list) {
				set.add(prescriptionmDto.getDrugstoreId());
			}
			List<DrugPre> drugPres = new ArrayList<>();
			for (Integer i : set) {
				DrugPre drugPre = new DrugPre();
				Drugstoreinfo drugstoreinfo = drugstoreinfoDao.getByUid(i);
				drugPre.setDrugstoreinfo(drugstoreinfo);
				ArrayList<PrescriptionmDto> arrayList = new ArrayList<>();
				for (PrescriptionmDto prescriptionmDto : list) {
					if(prescriptionmDto.getDrugstoreId() == drugstoreinfo.getUid()){
						arrayList.add(prescriptionmDto);
					}
				}
				drugPre.setPrescriptionmDtos(arrayList);
				drugPres.add(drugPre);
			}
			
			return ResponseEntity.ok(drugPres);
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
			List<drugstoremDto> list = prescriptionmDao.drugsGetList(userId);
			for (drugstoremDto drugstoremDto : list) {
				List<Prescriptionm> prescriptionms = prescriptionmDao.selectPro(drugstoremDto.getTaskId(),drugstoremDto.getDrugstoreId());
				drugstoremDto.setPrescriptionms(prescriptionms);
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
			prescriptionmDao.updateStatus(taskId,userId);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body(null);
		}
	}
	
}
