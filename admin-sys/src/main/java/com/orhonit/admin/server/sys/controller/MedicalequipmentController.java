package com.orhonit.admin.server.sys.controller;



import java.util.List;

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
import com.orhonit.admin.server.sys.model.Medicalequipment;
import com.orhonit.admin.server.sys.service.MedicalequipmentSerivce;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/medicalequipments")
public class MedicalequipmentController {

    @Autowired
    private MedicalequipmentSerivce medicalequipmentService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Medicalequipment save(@RequestBody Medicalequipment medicalequipment) {
    	medicalequipmentService.save(medicalequipment);

        return medicalequipment;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Medicalequipment get(@PathVariable Long id) {
        return medicalequipmentService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Medicalequipment update(@RequestBody Medicalequipment medicalequipment) {
    	medicalequipmentService.update(medicalequipment);

        return medicalequipment;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Medicalequipment> list(TableRequest request) {
    	return medicalequipmentService.list(request);
       
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	medicalequipmentService.delete(id);
    }
    
    @GetMapping("/all")
    @ApiOperation(value = "查询所有")
    public List<Medicalequipment> all(){
    	return medicalequipmentService.all();
    }
}
