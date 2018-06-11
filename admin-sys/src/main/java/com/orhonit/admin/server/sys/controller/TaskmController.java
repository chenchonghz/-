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
    @GetMapping("/updateGood")
    @ApiOperation(value = "设置病例是否为优秀")
    public int updateGood(Long id,Integer good){
    	return taskmDaoService.updateGood(id,good);
    }
    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Taskm> list(TableRequest request) {
    	return taskmDaoService.list(request);
    }
    @GetMapping("/AppList")
    @ApiOperation("App拿到诊断列表，根据token判断专家还是牧民")
    public ResponseEntity<?> AppList(){
    	return taskmDaoService.AppList();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	taskmDaoService.delete(id);
    }
    
    /**
     * @author: 孙少辉
     * @data: 2018年4月25日
     * @param id
     * @return  
     * @Description:  App端的假删除  
     */
    @GetMapping("AppUpdate/{id}")
    @ApiOperation(value = "app端删除，假删除，改状态")
    public ResponseEntity<?> AppDelete(@PathVariable Long id){
    	return taskmDaoService.AppDelete(id);
    }
}
