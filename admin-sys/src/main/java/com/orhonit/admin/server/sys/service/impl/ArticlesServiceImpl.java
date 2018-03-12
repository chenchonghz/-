package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.ArticlesDao;
import com.orhonit.admin.server.sys.model.Articles;
import com.orhonit.admin.server.sys.service.ArticlesService;

@Service
public class ArticlesServiceImpl implements ArticlesService {
	@Autowired
	private ArticlesDao articlesDao;

	@Override
	public void save(Articles articles) {
		// TODO Auto-generated method stub
		articlesDao.save(articles);
	}

	@Override
	public Articles getById(Long id) {
		// TODO Auto-generated method stub
		return articlesDao.getById(id);
	}

	@Override
	public void update(Articles articles) {
		// TODO Auto-generated method stub
		articlesDao.update(articles);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		articlesDao.delete(id);
	}

	@Override
	public TableResponse<Articles> listArticles(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Articles> builder().countHandler(new CountHandler() {

			@Override
			public int count(TableRequest request) {
				return articlesDao.count(request.getParams());
			}
		}).listHandler(new ListHandler<Articles>() {

			@Override
			public List<Articles> list(TableRequest request) {
				return articlesDao.list(request.getParams(), request.getStart(), request.getLength());
			}
		}).build().handle(request);
	}
}
