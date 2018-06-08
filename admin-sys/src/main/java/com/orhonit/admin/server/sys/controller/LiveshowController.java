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
import com.orhonit.admin.server.sys.model.Liveshow;
import com.orhonit.admin.server.sys.service.LiveshowService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/liveshows")
public class LiveshowController {

    @Autowired
    private LiveshowService liveshowService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Liveshow save(@RequestBody Liveshow liveshow) {
    	liveshowService.save(liveshow);

        return liveshow;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Liveshow get(@PathVariable Long id) {
        return liveshowService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Liveshow update(@RequestBody Liveshow liveshow) {
    	liveshowService.update(liveshow);

        return liveshow;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Liveshow> list(TableRequest request) {
    	return liveshowService.list(request);
        
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	liveshowService.delete(id);
    }
    @GetMapping("pass/{id}")
    @ApiOperation(value="文章审核通过")
    public int LiveShowPass(@PathVariable Long id) {
    	return liveshowService.liveshowPass(id);
    }
    
    @GetMapping("fail/{id}")
    @ApiOperation(value="文章审核失败")
    public int LiveShowPassFail(@PathVariable Long id) {
    	return liveshowService.liveshowFail(id);
    }
    @PostMapping("/App/AddLiveShow")
    @ApiOperation(value = "手机端专家申请一个直播")
    public ResponseEntity<?> AddLiveShow(Liveshow liveshow){
    	return liveshowService.AddLiveShow(liveshow);
    }
    
    @GetMapping("/App/getLiveNow")
    @ApiOperation(value = "拿到当前时间正在直播的")
    public ResponseEntity<?> getLiveNow(){
    	return liveshowService.getLiveNow();
    }
    @GetMapping("/App/addPeople")
    @ApiOperation(value = "直播间添加人")
    public ResponseEntity<?> addPeople(Integer id){
    	return liveshowService.addPeople(id);
    }
    @GetMapping("/App/lessPeople")
    @ApiOperation(value = "直播间减少人")
    public ResponseEntity<?> lessPeople(Integer id){
    	return liveshowService.lessPeople(id);
    }
    
}
