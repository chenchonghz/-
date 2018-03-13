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
import com.orhonit.admin.server.sys.model.Educationm;
import com.orhonit.admin.server.sys.service.EducationmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/educationms")
public class EducationmController {

    @Autowired
    private EducationmService educationmService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Educationm save(@RequestBody Educationm educationm) {
    	educationmService.save(educationm);

        return educationm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Educationm get(@PathVariable Long id) {
        return educationmService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Educationm update(@RequestBody Educationm educationm) {
    	educationmService.update(educationm);

        return educationm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Educationm> list(TableRequest request) {
    	return educationmService.list(request);
        
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	educationmService.delete(id);
    }
}
