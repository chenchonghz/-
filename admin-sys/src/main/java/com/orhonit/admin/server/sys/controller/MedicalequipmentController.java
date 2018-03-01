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
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.dao.MedicalequipmentDao;
import com.orhonit.admin.server.sys.model.Medicalequipment;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/medicalequipments")
public class MedicalequipmentController {

    @Autowired
    private MedicalequipmentDao medicalequipmentDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Medicalequipment save(@RequestBody Medicalequipment medicalequipment) {
        medicalequipmentDao.save(medicalequipment);

        return medicalequipment;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Medicalequipment get(@PathVariable Long id) {
        return medicalequipmentDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Medicalequipment update(@RequestBody Medicalequipment medicalequipment) {
        medicalequipmentDao.update(medicalequipment);

        return medicalequipment;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Medicalequipment> list(TableRequest request) {
        return TableRequestHandler.<Medicalequipment> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return medicalequipmentDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Medicalequipment>() {

            @Override
            public List<Medicalequipment> list(TableRequest request) {
                return medicalequipmentDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        medicalequipmentDao.delete(id);
    }
}
