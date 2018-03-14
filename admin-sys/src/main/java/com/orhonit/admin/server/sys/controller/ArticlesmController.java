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
import com.orhonit.admin.server.sys.model.Articlesm;
import com.orhonit.admin.server.sys.service.ArticlesmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/articlesms")
public class ArticlesmController {

    @Autowired
    private ArticlesmService articlesmService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Articlesm save(@RequestBody Articlesm articlesm) {
    	articlesmService.save(articlesm);

        return articlesm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Articlesm get(@PathVariable Long id) {
        return articlesmService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Articlesm update(@RequestBody Articlesm articlesm) {
    	articlesmService.update(articlesm);

        return articlesm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Articlesm> list(TableRequest request) {
    	return articlesmService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	articlesmService.delete(id);
    }
}
