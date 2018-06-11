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
    @GetMapping("/updateGood")
    @ApiOperation(value = "设置病例是否为优秀")
    public int updateGood(Long id,Integer good){
    	return taskService.updateGood(id,good);
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
    /**
     * @author: 孙少辉
     * @data: 2018年4月25日
     * @return  
     * @Description: App拿到诊断列表，根据token判断专家还是牧民 
     */
    @GetMapping("/AppList")
    @ApiOperation("App拿到诊断列表，根据token判断专家还是牧民")
    public ResponseEntity<?> AppList(){
    	return taskService.AppList();
    }
    
    /**
     * @author: 孙少辉
     * @data: 2018年4月25日
     * @param id
     * @return  
     * @Description: App端的假删除 
     */
    @GetMapping("AppUpdate/{id}")
    @ApiOperation(value = "app端删除，假删除，改状态")
    public ResponseEntity<?> AppDelete(@PathVariable Long id){
    	return taskService.AppDelete(id);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	taskService.delete(id);
    }
}
