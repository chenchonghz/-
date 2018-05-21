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
import com.orhonit.admin.server.sys.model.Liveshowm;
import com.orhonit.admin.server.sys.service.LiveshowmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/liveshowms")
public class LiveshowmController {

    @Autowired
    private LiveshowmService liveshowmService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Liveshowm save(@RequestBody Liveshowm liveshowm) {
    	liveshowmService.save(liveshowm);

        return liveshowm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Liveshowm get(@PathVariable Long id) {
        return liveshowmService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Liveshowm update(@RequestBody Liveshowm liveshowm) {
    	liveshowmService.update(liveshowm);

        return liveshowm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Liveshowm> list(TableRequest request) {
    	return liveshowmService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	liveshowmService.delete(id);
    }
    
    @PostMapping("/App/AddLiveShow")
    @ApiOperation(value = "手机端专家申请一个直播")
    public ResponseEntity<?> AddLiveShow(Liveshowm liveshowm){
    	return liveshowmService.AddLiveShow(liveshowm);
    }
    @GetMapping("/App/getLiveNow")
    @ApiOperation(value = "拿到当前时间正在直播的")
    public ResponseEntity<?> getLiveNow(){
    	return liveshowmService.getLiveNow();
    }
}
