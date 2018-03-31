package com.orhonit.admin.server.sys.controller;

import java.util.List;

import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Videoconnect;
import com.orhonit.admin.server.sys.service.VideoconnectService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/videoconnects")
public class VideoconnectController {

	
    @Autowired
    private VideoconnectService videoconnectService;
   
    @PostMapping
    @ApiOperation(value = "保存")
    public Videoconnect save(@RequestBody Videoconnect videoconnect) {
    	videoconnectService.save(videoconnect);

        return videoconnect;
    }
    
    @GetMapping("/vc")
    @ApiOperation(value = "发起视频会议")
    public Videoconnect saveVc(@RequestParam(value="eid") String eid) {
    	Videoconnect videoconnect = videoconnectService.saveVc(Integer.parseInt(eid));
    	if(videoconnect == null){
    		throw new UnknownAccountException("系统错误");
    	}
    	return videoconnect;
    	
    	
    }
   
    

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Videoconnect get(@PathVariable Long id) {
        return videoconnectService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Videoconnect update(@RequestBody Videoconnect videoconnect) {
    	videoconnectService.update(videoconnect);

        return videoconnect;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Videoconnect> list(TableRequest request) {
        return TableRequestHandler.<Videoconnect> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return videoconnectService.count(request.getParams());
            }
        }).listHandler(new ListHandler<Videoconnect>() {

            @Override
            public List<Videoconnect> list(TableRequest request) {
                return videoconnectService.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	videoconnectService.delete(id);
    }
    
   
    
    
}
