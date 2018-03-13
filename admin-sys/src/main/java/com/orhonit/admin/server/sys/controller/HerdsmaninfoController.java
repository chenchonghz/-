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
import com.orhonit.admin.server.sys.model.Herdsmaninfo;
import com.orhonit.admin.server.sys.service.HerdsmaninfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/herdsmaninfos")
public class HerdsmaninfoController {

    @Autowired
    private HerdsmaninfoService herdsmaninfoService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Herdsmaninfo save(@RequestBody Herdsmaninfo herdsmaninfo) {
    	herdsmaninfoService.save(herdsmaninfo);

        return herdsmaninfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Herdsmaninfo get(@PathVariable Long id) {
        return herdsmaninfoService.getById(id);
    }
    
    @GetMapping("herdsmaninfo/{id}")
    @ApiOperation(value = "根据uid获取")
    public Herdsmaninfo get(@PathVariable int id) {
        return herdsmaninfoService.getByUid(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Herdsmaninfo update(@RequestBody Herdsmaninfo herdsmaninfo) {
    	herdsmaninfoService.update(herdsmaninfo);

        return herdsmaninfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Herdsmaninfo> list(TableRequest request) {
    	return herdsmaninfoService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	herdsmaninfoService.delete(id);
    }
}
