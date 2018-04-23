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
import com.orhonit.admin.server.sys.model.Slidem;
import com.orhonit.admin.server.sys.service.SlidemService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/slidems")
public class SlidemController {

	@Autowired
    private SlidemService slidemService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Slidem save(@RequestBody Slidem slidem) {
    	slidemService.save(slidem);

        return slidem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Slidem get(@PathVariable Long id) {
        return slidemService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Slidem update(@RequestBody Slidem slidem) {
    	slidemService.update(slidem);

        return slidem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Slidem> list(TableRequest request) {
    	return slidemService.list(request);
        
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	slidemService.delete(id);
    }
}
