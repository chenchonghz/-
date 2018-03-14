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
import com.orhonit.admin.server.sys.model.Drug;
import com.orhonit.admin.server.sys.service.DrugService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/drugs")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Drug save(@RequestBody Drug drug) {
    	drugService.save(drug);

        return drug;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Drug get(@PathVariable Long id) {
        return drugService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Drug update(@RequestBody Drug drug) {
    	drugService.update(drug);

        return drug;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Drug> list(TableRequest request) {
    	return drugService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	drugService.delete(id);
    }
}
