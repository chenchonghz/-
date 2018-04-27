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
import com.orhonit.admin.server.sys.model.Prescriptionm;
import com.orhonit.admin.server.sys.service.PrescriptionmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prescriptionms")
public class PrescriptionmController {

    @Autowired
    private PrescriptionmService prescriptionmService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Prescriptionm save(@RequestBody Prescriptionm prescriptionm) {
    	prescriptionmService.save(prescriptionm);

        return prescriptionm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Prescriptionm get(@PathVariable Long id) {
        return prescriptionmService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Prescriptionm update(@RequestBody Prescriptionm prescriptionm) {
    	prescriptionmService.update(prescriptionm);

        return prescriptionm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Prescriptionm> list(TableRequest request) {
    	return prescriptionmService.list(request);
        
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	prescriptionmService.delete(id);
    }
    @GetMapping("/App/getP/{taskId}")
    @ApiOperation(value = "App端拿到药品数据，牧民专家同一个")
    public ResponseEntity<?> getP(@PathVariable Long taskId){
    	return prescriptionmService.getP(taskId);
    }
}
