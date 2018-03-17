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
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.service.TaskService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Task save(@RequestBody Task task) {
    	taskService.save(task);

        return task;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Task get(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Task update(@RequestBody Task task) {
    	taskService.update(task);

        return task;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Task> list(TableRequest request) {
    	return taskService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	taskService.delete(id);
    }
}
