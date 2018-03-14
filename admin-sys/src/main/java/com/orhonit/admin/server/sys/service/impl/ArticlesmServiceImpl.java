package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.ArticlesmDao;
import com.orhonit.admin.server.sys.model.Articlesm;
import com.orhonit.admin.server.sys.service.ArticlesmService;

@Service
public class ArticlesmServiceImpl implements ArticlesmService {
	 @Autowired
	 private ArticlesmDao articlesmDao;

	@Override
	public void save(Articlesm articlesm) {
		// TODO Auto-generated method stub
		articlesmDao.save(articlesm);
	}

	@Override
	public Articlesm getById(Long id) {
		// TODO Auto-generated method stub
		return articlesmDao.getById(id);
	}

	@Override
	public void update(Articlesm articlesm) {
		// TODO Auto-generated method stub
		articlesmDao.update(articlesm);
	}

	@Override
	public TableResponse<Articlesm> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Articlesm> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return articlesmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Articlesm>() {

            @Override
            public List<Articlesm> list(TableRequest request) {
                return articlesmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		articlesmDao.delete(id);
	}
}
