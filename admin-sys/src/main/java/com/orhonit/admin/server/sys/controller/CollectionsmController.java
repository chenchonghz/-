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
import com.orhonit.admin.server.sys.model.Collectionsm;
import com.orhonit.admin.server.sys.service.CollectionsmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/collectionsms")
public class CollectionsmController {

    @Autowired
    private CollectionsmService collectionsmService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Collectionsm save(@RequestBody Collectionsm collectionsm) {
    	collectionsmService.save(collectionsm);

        return collectionsm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Collectionsm get(@PathVariable Long id) {
        return collectionsmService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Collectionsm update(@RequestBody Collectionsm collectionsm) {
    	collectionsmService.update(collectionsm);

        return collectionsm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Collectionsm> list(TableRequest request) {
    	return collectionsmService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	collectionsmService.delete(id);
    }
    @PostMapping("/App/add")
    @ApiOperation(value = "App端添加收藏")
    public ResponseEntity<?> AppAdd(Integer cateId,Integer chlidrenId){
    	return collectionsmService.appAdd(cateId,chlidrenId);
    }
    @GetMapping("App/look")
    @ApiOperation(value = "当前用户查看自己的收藏")
    public ResponseEntity<?> Applook(){
    	return collectionsmService.Applook();
    }
    @GetMapping("App/delete")
    @ApiOperation(value = "当前用户删除自己的收藏")
    public ResponseEntity<?> AppDelete(Long id){
    	return collectionsmService.AppDelete(id);
    }
}
