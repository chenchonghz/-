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
import com.orhonit.admin.server.sys.model.Education;
import com.orhonit.admin.server.sys.service.EducationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/educations")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Education save(@RequestBody Education education) {
    	educationService.save(education);
        return education;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Education get(@PathVariable Long id) {
        return educationService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Education update(@RequestBody Education education) {
    	educationService.update(education);
        return education;
    }
  
    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Education> list(TableRequest request) {
    	return educationService.list(request);
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月23日
     * @return  
     * @Description: App端拿到list信息
     */
    @GetMapping("/getList")
    @ApiOperation(value = "APP端拿到列表信息")
    public ResponseEntity<?> getList(){
    	return educationService.getList();
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	educationService.delete(id);
    }
}
