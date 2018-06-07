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
import com.orhonit.admin.server.sys.dao.DrugstoreinfoDao;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.NewherdsmanDao;
import com.orhonit.admin.server.sys.dao.StudyArticleDao;
import com.orhonit.admin.server.sys.dao.StudyVideoDao;
import com.orhonit.admin.server.sys.dao.TaskDao;
import com.orhonit.admin.server.sys.dao.UserDao;
import com.orhonit.admin.server.sys.model.Comment;
import com.orhonit.admin.server.sys.model.User;
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

	@Autowired
	private UserDao userDao;
	@Autowired
	private NewherdsmanDao newherdsmanDao;
	@Autowired
	private ExpertinfoDao expertinfoDao;
	@Autowired
	private DrugstoreinfoDao drugstoreinfoDao;
	@Autowired
	private StudyArticleDao studyArticleDao;
	@Autowired
	private StudyVideoDao studyVideoDao;
	@Autowired
	private TaskDao taskDao;
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
                List<Comment> list = commentDao.list(request.getParams(), request.getStart(), request.getLength());
                for (Comment comment : list) {
					User user = userDao.getById(Long.parseLong(comment.getHerdsmanId().toString()));
					if(user.getType() == 1){
						comment.setName(newherdsmanDao.getUid(user.getId()).getName()+"(牧民)");
					}else if(user.getType() == 2){
						comment.setName(expertinfoDao.getUId(user.getId()).getName()+"(专家)");
					}else{
						comment.setName(drugstoreinfoDao.getUId(user.getId()).getPharmacyName()+"(药店)");
					}
					
					if(comment.getCateId() == 1){
						comment.setTitle(studyArticleDao.getById(Long.parseLong(comment.getChlidrenId().toString())).getTitle());
					}else if(comment.getCateId() == 2){
						comment.setTitle(studyVideoDao.getById(Long.parseLong(comment.getChlidrenId().toString())).getTitle());
					}else{
						comment.setTitle(taskDao.getById(Long.parseLong(comment.getChlidrenId().toString())).getTitle());
					}
				}
                return list;
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

	@Override
	public ResponseEntity<?> getCBycid(Integer cateId, Integer childrenId) {
		// TODO Auto-generated method stub
		try {
			List<Comment> list= commentDao.getCBycid(cateId,childrenId);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(401).body("错误");
			// TODO: handle exception
		}
	}

	@Override
	public int commentPass(Long id) {
		// TODO Auto-generated method stub
		Comment comment = commentDao.getById(id);
		comment.setStatus(1);
		return commentDao.updateStatue(comment);
	}

	@Override
	public int commentFail(Long id) {
		// TODO Auto-generated method stub
		Comment comment = commentDao.getById(id);
		comment.setStatus(2);
		return commentDao.updateStatue(comment);
	}
}
