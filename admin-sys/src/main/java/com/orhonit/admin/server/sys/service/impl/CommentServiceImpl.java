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
import com.orhonit.admin.server.sys.dao.CommentDao;
import com.orhonit.admin.server.sys.model.Comment;
import com.orhonit.admin.server.sys.service.CommentService;
import com.orhonit.admin.server.sys.utils.UserUtil;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
    private CommentDao commentDao;

	@Override
	public void save(Comment comment) {
		// TODO Auto-generated method stub
		commentDao.save(comment);
	}

	@Override
	public Comment getById(Long id) {
		// TODO Auto-generated method stub
		return commentDao.getById(id);
	}

	@Override
	public void update(Comment comment) {
		// TODO Auto-generated method stub
		commentDao.update(comment);
	}

	@Override
	public TableResponse<Comment> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Comment> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return commentDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Comment>() {

            @Override
            public List<Comment> list(TableRequest request) {
                return commentDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		commentDao.delete(id);
	}

	@Override
	public ResponseEntity<?> saveCom(Comment comment) {
		// TODO Auto-generated method stub
		try {
			Long userId = UserUtil.getCurrentUser().getId();
			comment.setHerdsmanId(Integer.parseInt(userId.toString()));
			comment.setStatus(0);
			commentDao.save(comment);
			return ResponseEntity.ok(null);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> examine(Integer id) {
		// TODO Auto-generated method stub
		try {
			commentDao.examine(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(401).body("错误");
		}
	}

	@Override
	public ResponseEntity<?> getList() {
		// TODO Auto-generated method stub
		try {
			List<Comment> list= commentDao.getList();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(401).body("错误");
			// TODO: handle exception
		}
	}
}
