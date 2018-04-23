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
import com.orhonit.admin.server.sys.model.Slide;
import com.orhonit.admin.server.sys.service.SlideService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private SlideService slideService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Slide save(@RequestBody Slide slide) {
    	slideService.save(slide);

        return slide;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Slide get(@PathVariable Long id) {
        return slideService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Slide update(@RequestBody Slide slide) {
    	slideService.update(slide);

        return slide;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Slide> list(TableRequest request) {
    	return slideService.list(request);
       
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	slideService.delete(id);
    } 
}
