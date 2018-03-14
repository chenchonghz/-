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
import com.orhonit.admin.server.sys.model.Categorym;
import com.orhonit.admin.server.sys.service.CategorymService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categoryms")
public class CategorymController {

    @Autowired
    private CategorymService categorymService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Categorym save(@RequestBody Categorym categorym) {
    	categorymService.save(categorym);

        return categorym;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Categorym get(@PathVariable Long id) {
        return categorymService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Categorym update(@RequestBody Categorym categorym) {
    	categorymService.update(categorym);

        return categorym;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Categorym> list(TableRequest request) {
    	return categorymService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	categorymService.delete(id);
    }
}
