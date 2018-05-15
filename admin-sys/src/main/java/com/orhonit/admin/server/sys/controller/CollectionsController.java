package com.orhonit.admin.server.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.orhonit.admin.server.sys.model.Collections;
import com.orhonit.admin.server.sys.service.CollectionsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/collectionss")
public class CollectionsController {

    @Autowired
    private CollectionsService collectionsService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Collections save(@RequestBody Collections collections) {
    	collectionsService.save(collections);

        return collections;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Collections get(@PathVariable Long id) {
        return collectionsService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Collections update(@RequestBody Collections collections) {
    	collectionsService.update(collections);

        return collections;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Collections> list(TableRequest request) {
    	return collectionsService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	collectionsService.delete(id);
    }
    
    @PostMapping("/App/add")
    @ApiOperation(value = "App端添加收藏")
    public ResponseEntity<?> AppAdd(Integer cateId,Integer chlidrenId){
    	return collectionsService.appAdd(cateId,chlidrenId);
    }
    @GetMapping("App/look")
    @ApiOperation(value = "当前用户查看自己的收藏")
    public ResponseEntity<?> Applook(Integer cateId){
    	return collectionsService.Applook(cateId);
    }
    @GetMapping("App/delete")
    @ApiOperation(value = "当前用户删除自己的收藏")
    public ResponseEntity<?> AppDelete(Long id){
    	return collectionsService.AppDelete(id);
    }
}
