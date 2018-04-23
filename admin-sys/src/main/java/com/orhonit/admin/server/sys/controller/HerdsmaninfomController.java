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
import com.orhonit.admin.server.sys.model.Herdsmaninfom;
import com.orhonit.admin.server.sys.service.HerdsmaninfomService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/herdsmaninfoms")
public class HerdsmaninfomController {

    @Autowired
    private HerdsmaninfomService herdsmaninfomService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Herdsmaninfom save(@RequestBody Herdsmaninfom herdsmaninfom) {
    	herdsmaninfomService.save(herdsmaninfom);

        return herdsmaninfom;
    }
   
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Herdsmaninfom get(@PathVariable Long id) {
        return herdsmaninfomService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Herdsmaninfom update(@RequestBody Herdsmaninfom herdsmaninfom) {
    	herdsmaninfomService.update(herdsmaninfom);

        return herdsmaninfom;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Herdsmaninfom> list(TableRequest request) {
    	return herdsmaninfomService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	herdsmaninfomService.delete(id);
    }
}
