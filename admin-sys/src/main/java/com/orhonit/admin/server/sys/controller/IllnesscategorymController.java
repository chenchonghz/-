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
import com.orhonit.admin.server.sys.model.Illnesscategorym;
import com.orhonit.admin.server.sys.service.IllnesscategorymService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/illnesscategoryms")
public class IllnesscategorymController {

    @Autowired
    private IllnesscategorymService illnesscategorymService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Illnesscategorym save(@RequestBody Illnesscategorym illnesscategorym) {
    	illnesscategorymService.save(illnesscategorym);

        return illnesscategorym;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Illnesscategorym get(@PathVariable Long id) {
        return illnesscategorymService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Illnesscategorym update(@RequestBody Illnesscategorym illnesscategorym) {
    	illnesscategorymService.update(illnesscategorym);

        return illnesscategorym;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Illnesscategorym> list(TableRequest request) {
    	return illnesscategorymService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	illnesscategorymService.delete(id);
    }
}
