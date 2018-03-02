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
import com.orhonit.admin.server.sys.dao.EducationDao;
import com.orhonit.admin.server.sys.model.Education;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/educations")
public class EducationController {

    @Autowired
    private EducationDao educationDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Education save(@RequestBody Education education) {
        educationDao.save(education);

        return education;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Education get(@PathVariable Long id) {
        return educationDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Education update(@RequestBody Education education) {
        educationDao.update(education);

        return education;
    }
  
    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Education> list(TableRequest request) {
        return TableRequestHandler.<Education> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return educationDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Education>() {

            @Override
            public List<Education> list(TableRequest request) {
                return educationDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        educationDao.delete(id);
    }
}
