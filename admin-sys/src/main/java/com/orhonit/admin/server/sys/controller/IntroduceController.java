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
import com.orhonit.admin.server.sys.model.Introduce;
import com.orhonit.admin.server.sys.service.IntroduceService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/introduces")
public class IntroduceController {

    @Autowired
    private IntroduceService introduceService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Introduce save(@RequestBody Introduce introduce) {
    	introduceService.save(introduce);

        return introduce;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Introduce get(@PathVariable Long id) {
        return introduceService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Introduce update(@RequestBody Introduce introduce) {
    	introduceService.update(introduce);

        return introduce;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Introduce> list(TableRequest request) {
    	return introduceService.list(request);
        
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	introduceService.delete(id);
    }
    @GetMapping("/App/getAll")
    @ApiOperation(value = "手机端拿到所有说明")
    public ResponseEntity<?> getAll(){
    	return introduceService.getAll();
    }
}
