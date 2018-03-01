package com.orhonit.admin.server.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.dao.CategoryDao;
import com.orhonit.admin.server.sys.model.Category;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Category save(@RequestBody Category category) {
        categoryDao.save(category);

        return category;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Category get(@PathVariable Long id) {
        return categoryDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Category update(@RequestBody Category category) {
        categoryDao.update(category);

        return category;
    }
    @GetMapping("/parents")
    @ApiOperation(value = "一级菜单")
    public List<Category> parentCategory() {
    	List<Category> listAll= categoryDao.listAll();
        return listAll;
    }
    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Category> list(TableRequest request) {
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

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        categoryDao.delete(id);
    }
}
