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
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.service.TaskmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/taskms")
public class TaskmController {

    @Autowired
    private TaskmService taskmDaoService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Taskm save(@RequestBody Taskm taskm) {
    	taskmDaoService.save(taskm);

        return taskm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Taskm get(@PathVariable Long id) {
        return taskmDaoService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Taskm update(@RequestBody Taskm taskm) {
    	taskmDaoService.update(taskm);

        return taskm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Taskm> list(TableRequest request) {
    	return taskmDaoService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	taskmDaoService.delete(id);
    }
}
