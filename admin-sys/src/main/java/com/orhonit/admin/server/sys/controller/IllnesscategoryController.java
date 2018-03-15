package com.orhonit.admin.server.sys.controller;


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
import com.orhonit.admin.server.sys.model.Illnesscategory;
import com.orhonit.admin.server.sys.service.illnesscategoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/illnesscategorys")
public class IllnesscategoryController {

    @Autowired
    private illnesscategoryService illnesscategoryService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Illnesscategory save(@RequestBody Illnesscategory illnesscategory) {
    	illnesscategoryService.save(illnesscategory);

        return illnesscategory;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Illnesscategory get(@PathVariable Long id) {
        return illnesscategoryService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Illnesscategory update(@RequestBody Illnesscategory illnesscategory) {
    	illnesscategoryService.update(illnesscategory);

        return illnesscategory;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Illnesscategory> list(TableRequest request) {
    	return illnesscategoryService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	illnesscategoryService.delete(id);
    }
}
