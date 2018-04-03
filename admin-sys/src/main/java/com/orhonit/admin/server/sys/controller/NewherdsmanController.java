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
import com.orhonit.admin.server.sys.model.Newherdsman;
import com.orhonit.admin.server.sys.service.NewherdsmanService;

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
