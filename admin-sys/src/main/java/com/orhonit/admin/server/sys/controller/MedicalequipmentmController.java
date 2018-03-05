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
import com.orhonit.admin.server.sys.dao.MedicalequipmentmDao;
import com.orhonit.admin.server.sys.model.Medicalequipmentm;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/medicalequipmentms")
public class MedicalequipmentmController {

    @Autowired
    private MedicalequipmentmDao medicalequipmentmDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Medicalequipmentm save(@RequestBody Medicalequipmentm medicalequipmentm) {
        medicalequipmentmDao.save(medicalequipmentm);

        return medicalequipmentm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Medicalequipmentm get(@PathVariable Long id) {
        return medicalequipmentmDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Medicalequipmentm update(@RequestBody Medicalequipmentm medicalequipmentm) {
        medicalequipmentmDao.update(medicalequipmentm);

        return medicalequipmentm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Medicalequipmentm> list(TableRequest request) {
        return TableRequestHandler.<Medicalequipmentm> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return medicalequipmentmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Medicalequipmentm>() {

            @Override
            public List<Medicalequipmentm> list(TableRequest request) {
                return medicalequipmentmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        medicalequipmentmDao.delete(id);
    }
}
