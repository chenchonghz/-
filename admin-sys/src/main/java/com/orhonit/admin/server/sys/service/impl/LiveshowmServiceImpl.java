package com.orhonit.admin.server.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.LiveshowmDao;
import com.orhonit.admin.server.sys.model.Liveshow;
import com.orhonit.admin.server.sys.model.Liveshowm;
import com.orhonit.admin.server.sys.service.LiveshowmService;
import com.orhonit.admin.server.sys.utils.UserUtil;
@Service
public class LiveshowmServiceImpl implements LiveshowmService {
	@Autowired
    private LiveshowmDao liveshowmDao;

	@Override
	public void save(Liveshowm liveshowm) {
		// TODO Auto-generated method stub
		liveshowmDao.save(liveshowm);
	}

	@Override
	public Liveshowm getById(Long id) {
		// TODO Auto-generated method stub
		return liveshowmDao.getId(id);
	}

	@Override
	public void update(Liveshowm liveshowm) {
		// TODO Auto-generated method stub
		liveshowmDao.update(liveshowm);
	}
	
	@Autowired
	private ExpertinfoDao expertinfoDao;
	@Override
	public TableResponse<Liveshowm> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Liveshowm> builder().countHandler(new CountHandler() {
            @Override
            public int count(TableRequest request) {
                return liveshowmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Liveshowm>() {

            @Override
            public List<Liveshowm> list(TableRequest request) {
                List<Liveshowm> list = liveshowmDao.list(request.getParams(), request.getStart(), request.getLength());
                for (Liveshowm liveshowm : list) {
					liveshowm.setName(expertinfoDao.getUId(Long.parseLong(liveshowm.getOnlineApplyId().toString())).getNameMeng());
				}
                return list;
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		liveshowmDao.delete(id);
	}

	@Override
	public ResponseEntity<?> AddLiveShow(Liveshowm liveshowm) {
		// TODO Auto-generated method stub
		try {
			Long userId = UserUtil.getCurrentUser().getId();
			liveshowm.setOnlineApplyId(Integer.parseInt(userId.toString()));
			liveshowm.setOnlineQuantity(0);
			liveshowm.setLiveHome("假数据--直播房间");
			liveshowm.setStatus(0);
			liveshowmDao.save(liveshowm);
			return ResponseEntity.ok(liveshowm.getLiveHome());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> getLiveNow() {
		// TODO Auto-generated method stub
		try {
			Date date = new Date();
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			String time = formatter.format(date);
			List<Liveshow> list = liveshowmDao.getLiveNow(time);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> addPeople(Integer id) {
		// TODO Auto-generated method stub
		try {
			liveshowmDao.addPeople(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> lessPeople(Integer id) {
		// TODO Auto-generated method stub
		try {
			liveshowmDao.lessPeople(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public int liveshowmPass(Long id) {
		// TODO Auto-generated method stub
		Liveshowm liveshowm = liveshowmDao.getById(id);
		liveshowm.setStatus(1);
		return liveshowmDao.updateStatus(liveshowm);
	}

	@Override
	public int liveshowmFail(Long id) {
		// TODO Auto-generated method stub
		Liveshowm liveshowm = liveshowmDao.getById(id);
		liveshowm.setStatus(2);
		return liveshowmDao.updateStatus(liveshowm);
	}
}
