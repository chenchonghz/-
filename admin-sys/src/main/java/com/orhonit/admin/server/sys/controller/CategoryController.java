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
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Category;
import com.orhonit.admin.server.sys.service.CategoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Category save(@RequestBody Category category) {
    	categoryService.save(category);

        return category;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Category get(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Category update(@RequestBody Category category) {
    	categoryService.update(category);
        return category;
    }
    @GetMapping("/parents")
    @ApiOperation(value = "一级菜单")
    public List<Category> parentCategory() {
    	List<Category> listAll= categoryService.listAll();
        return listAll;
    }
    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Category> list(TableRequest request) {
    	 return categoryService.list(request);
       
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
