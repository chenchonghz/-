package com.orhonit.admin.server.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.sys.dao.CategoryDao;
import com.orhonit.admin.server.sys.model.Category;
import com.orhonit.admin.server.sys.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryDao categoryDao;

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	@Override
	public Category getById(Long id) {
		// TODO Auto-generated method stub
		return categoryDao.getById(id);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return categoryDao.listAll();
	}

	@Override
	public TableResponse<Category> list(TableRequest request) {
		// TODO Auto-generated method stub
		return TableRequestHandler.<Category> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return categoryDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Category>() {

            @Override
            public List<Category> list(TableRequest request) {
            	List<Category> list= categoryDao.list(request.getParams(), request.getStart(), request.getLength());
            	for (Category category : list) {
					if(category.getParentId()==0) {
						category.setPName("一级分类");
					}else {
						Category cate = categoryDao.getByParentId(category.getParentId());
						category.setPName(cate.getName());
					}
				}
            	return list;
                
            }
        }).build().handle(request);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		categoryDao.delete(id);
	}

}
