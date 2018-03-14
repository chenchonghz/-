package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.CommentmDao;
import com.orhonit.admin.server.sys.model.Commentm;
import com.orhonit.admin.server.sys.service.CommentmService;

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
                return commentmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		commentmDao.delete(id);
	}
}