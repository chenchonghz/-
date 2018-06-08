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
import com.orhonit.admin.server.sys.dao.CommentmDao;
import com.orhonit.admin.server.sys.dao.DrugstoreinfoDao;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.NewherdsmanDao;
import com.orhonit.admin.server.sys.dao.StudyarticlemDao;
import com.orhonit.admin.server.sys.dao.StudyvideomDao;
import com.orhonit.admin.server.sys.dao.TaskmDao;
import com.orhonit.admin.server.sys.dao.UserDao;
import com.orhonit.admin.server.sys.model.Commentm;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.service.CommentmService;
import com.orhonit.admin.server.sys.utils.UserUtil;


@Service
public class CommentmServiceImpl implements CommentmService {
	@Autowired
    private CommentmDao commentmDao;

	@Override
	public void save(Commentm commentm) {
		// TODO Auto-generated method stub
		commentmDao.save(commentm);
	}

	@Override
	public Commentm getById(Long id) {
		// TODO Auto-generated method stub
		return commentmDao.getById(id);
	}

	@Override
	public void update(Commentm commentm) {
		// TODO Auto-generated method stub
		commentmDao.update(commentm);
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
	private StudyarticlemDao studyarticlemDao;
	@Autowired
	private StudyvideomDao studyvideomDao;
	@Autowired
	private TaskmDao taskmDao;
	@Override
	public TableResponse<Commentm> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Commentm> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return commentmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Commentm>() {

            @Override
            public List<Commentm> list(TableRequest request) {
                List<Commentm> list = commentmDao.list(request.getParams(), request.getStart(), request.getLength());
                for (Commentm commentm : list) {
                	User user = userDao.getById(Long.parseLong(commentm.getHerdsmanId().toString()));
					if(user.getType() == 1){
						commentm.setName(newherdsmanDao.getUid(user.getId()).getNameMeng()+"(牧民)");
					}else if(user.getType() == 2){
						commentm.setName(expertinfoDao.getUId(user.getId()).getNameMeng()+"(专家)");
					}else{
						commentm.setName(drugstoreinfoDao.getUId(user.getId()).getPharmacyNameMeng()+"(药店)");
					}
					
					if(commentm.getCateId() == 1){
						commentm.setTitle(studyarticlemDao.getById(Long.parseLong(commentm.getChlidrenId().toString())).getTitle());
					}else if(commentm.getCateId() == 2){
						commentm.setTitle(studyvideomDao.getById(Long.parseLong(commentm.getChlidrenId().toString())).getTitle());
					}else{
						commentm.setTitle(taskmDao.getById(Long.parseLong(commentm.getChlidrenId().toString())).getTitle());
					}
				}
                return list;
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		commentmDao.delete(id);
	}
	

	@Override
	public ResponseEntity<?> saveCom(Commentm commentm) {
		// TODO Auto-generated method stub
		try {
			Long userId = UserUtil.getCurrentUser().getId();
			commentm.setHerdsmanId(Integer.parseInt(userId.toString()));
			commentm.setStatus(0);
			commentmDao.save(commentm);
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
			commentmDao.examine(id);
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
			List<Commentm> list= commentmDao.getList();
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
			List<Commentm> list= commentmDao.getCBycid(cateId,childrenId);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(401).body("错误");
			// TODO: handle exception
		}
	}

	@Override
	public int commentmFail(Long id) {
		// TODO Auto-generated method stub
		Commentm commentm = commentmDao.getById(id);
		commentm.setStatus(2);
		return commentmDao.updateStatus(commentm);
	}

	@Override
	public int commentmPass(Long id) {
		// TODO Auto-generated method stub
		Commentm commentm = commentmDao.getById(id);
		commentm.setStatus(1);
		return commentmDao.updateStatus(commentm);
	}
	
	
}
