package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.FeedbackDao;
import com.orhonit.admin.server.sys.model.Feedback;
import com.orhonit.admin.server.sys.service.FeedbackService;
import com.orhonit.admin.server.sys.utils.UserUtil;
@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
    private FeedbackDao feedbackDao;

	@Override
	public void save(Feedback feedback) {
		// TODO Auto-generated method stub
		feedbackDao.save(feedback);
	}

	@Override
	public Feedback getById(Long id) {
		// TODO Auto-generated method stub
		return feedbackDao.getById(id);
	}

	@Override
	public void update(Feedback feedback) {
		// TODO Auto-generated method stub
		feedbackDao.update(feedback);
	}

	@Override
	public TableResponse<Feedback> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Feedback> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return feedbackDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Feedback>() {

            @Override
            public List<Feedback> list(TableRequest request) {
                return feedbackDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		feedbackDao.delete(id);
	}

	@Override
	public ResponseEntity<?> Appsave(Feedback feedback) {
		// TODO Auto-generated method stub
		try {
			Long uid = UserUtil.getCurrentUser().getId();
			feedback.setUid(Integer.parseInt(uid.toString()));
			save(feedback);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(401).body("null");
		}
	}
}
