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
import com.orhonit.admin.server.sys.model.Prescription;
import com.orhonit.admin.server.sys.service.PrescriptionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Prescription save(@RequestBody Prescription prescription) {
    	prescriptionService.save(prescription);

        return prescription;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Prescription get(@PathVariable Long id) {
        return prescriptionService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Prescription update(@RequestBody Prescription prescription) {
    	prescriptionService.update(prescription);

        return prescription;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Prescription> list(TableRequest request) {
    	return prescriptionService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	prescriptionService.delete(id);
    }
    
    @GetMapping("/App/getP/{taskId}")
    @ApiOperation(value = "App端拿到药品数据，牧民专家同一个")
    public ResponseEntity<?> getP(@PathVariable Long taskId){
    	return prescriptionService.getP(taskId);
    }
    
    @GetMapping("/App/drugsGetList")
    @ApiOperation(value = "App端药店查看所有订单,汉语")
    public ResponseEntity<?> drugsGetList(){
    	return prescriptionService.drugsGetList();
    }
    
    @GetMapping("/App/updateStatus/{taskId}")
    @ApiOperation(value = "药店点击确认后改变状态")
    public ResponseEntity<?> updateStatus(@PathVariable Long taskId){
    	return prescriptionService.updateStatus(taskId);
    }
}
