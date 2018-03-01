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
import com.orhonit.admin.server.sys.dao.EducationmDao;
import com.orhonit.admin.server.sys.model.Educationm;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/educationms")
public class EducationmController {

    @Autowired
    private EducationmDao educationmDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Educationm save(@RequestBody Educationm educationm) {
        educationmDao.save(educationm);

        return educationm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Educationm get(@PathVariable Long id) {
        return educationmDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Educationm update(@RequestBody Educationm educationm) {
        educationmDao.update(educationm);

        return educationm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Educationm> list(TableRequest request) {
        return TableRequestHandler.<Educationm> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return educationmDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Educationm>() {

            @Override
            public List<Educationm> list(TableRequest request) {
                return educationmDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        educationmDao.delete(id);
    }
}
