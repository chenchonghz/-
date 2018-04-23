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
import com.orhonit.admin.server.sys.model.Newherdsman;
import com.orhonit.admin.server.sys.service.NewherdsmanService;
import com.orhonit.admin.server.sys.utils.UserUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/newherdsmans")
public class NewherdsmanController {

    @Autowired
    private NewherdsmanService newherdsmanService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Newherdsman save(@RequestBody Newherdsman newherdsman) {
    	newherdsmanService.save(newherdsman);

        return newherdsman;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Newherdsman get(@PathVariable Long id) {
        return newherdsmanService.getById(id);
    }
    @GetMapping("newherdsman/han/{id}")
    @ApiOperation(value = "根据uid获取")
    public Newherdsman get(@PathVariable int id) {
        return newherdsmanService.getByUid(id);
    }
    @GetMapping("newherdsman/meng/{id}")
    @ApiOperation(value = "根据uid获取")
    public Newherdsman getm(@PathVariable int id) {
    	return newherdsmanService.getByUidm(id);
    }
    @GetMapping("App/getByUid")
    @ApiOperation(value = "手机端根据uid获取")
    public ResponseEntity<?> getByUid(){
    	String string = UserUtil.getCurrentUser().getId().toString();
    	return newherdsmanService.AppGetByUid(Integer.parseInt(string));
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月23日
     * @param newherdsman
     * @return  
     * @Description: 手机端添加牧民信息 
     */
    @PostMapping("/save")
    @ApiOperation(value = "App端添加牧民个人信息")
    public ResponseEntity<?> Appsave(Newherdsman newherdsman){
    	return newherdsmanService.Appsave(newherdsman);
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月23日
     * @param newherdsman
     * @return  
     * @Description: App端修改牧民信息 
     */
    @PostMapping("/update")
    @ApiOperation(value = "App端修改牧民个人信息")
    public ResponseEntity<?> Appupdate(Newherdsman newherdsman){
    	return newherdsmanService.Appupdate(newherdsman);
    }
    @PutMapping
    @ApiOperation(value = "修改")
    public Newherdsman update(@RequestBody Newherdsman newherdsman) {
    	newherdsmanService.update(newherdsman);

        return newherdsman;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Newherdsman> list(TableRequest request) {
    	return newherdsmanService.list(request);
       
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	newherdsmanService.delete(id);
    }
}
