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
import com.orhonit.admin.server.sys.dao.LiveshowDao;
import com.orhonit.admin.server.sys.model.Liveshow;
import com.orhonit.admin.server.sys.service.LiveshowService;
import com.orhonit.admin.server.sys.utils.UserUtil;
@Service
public class LiveshowServiceImpl implements LiveshowService {
	@Autowired
    private LiveshowDao liveshowDao;

	@Override
	public void save(Liveshow liveshow) {
		// TODO Auto-generated method stub
		liveshowDao.save(liveshow);
	}

	@Override
	public Liveshow getById(Long id) {
		// TODO Auto-generated method stub
		return liveshowDao.getById(id);
	}

	@Override
	public void update(Liveshow liveshow) {
		// TODO Auto-generated method stub
		liveshowDao.update(liveshow);
	}

	@Override
	public TableResponse<Liveshow> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Liveshow> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return liveshowDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Liveshow>() {

            @Override
            public List<Liveshow> list(TableRequest request) {
                return liveshowDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		liveshowDao.delete(id);
	}

	@Override
	public ResponseEntity<?> AddLiveShow(Liveshow liveshow) {
		// TODO Auto-generated method stub
		try {
			Long userId = UserUtil.getCurrentUser().getId();
			liveshow.setOnlineApplyId(Integer.parseInt(userId.toString()));
			liveshow.setOnlineQuantity(0);
			liveshow.setLiveHome("假数据--直播房间");
			liveshowDao.save(liveshow);
			return ResponseEntity.ok(liveshow.getLiveHome());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			List<Liveshow> list = liveshowDao.getLiveNow(time);
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
			liveshowDao.addPeople(id);
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
			liveshowDao.lessPeople(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}
}
