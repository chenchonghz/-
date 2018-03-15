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
import com.orhonit.admin.server.sys.model.Studyarticlem;
import com.orhonit.admin.server.sys.service.StudyarticlemService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/studyarticlems")
public class StudyarticlemController {

    @Autowired
    private StudyarticlemService studyarticlemService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Studyarticlem save(@RequestBody Studyarticlem studyarticlem) {
    	studyarticlemService.save(studyarticlem);

        return studyarticlem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Studyarticlem get(@PathVariable Long id) {
        return studyarticlemService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Studyarticlem update(@RequestBody Studyarticlem studyarticlem) {
    	studyarticlemService.update(studyarticlem);

        return studyarticlem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Studyarticlem> list(TableRequest request) {
    	return studyarticlemService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	studyarticlemService.delete(id);
    }
}
