package com.orhonit.admin.server.sys.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.constants.EventType;
import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.common.event.AdminEvent;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.dao.VideoconnectDao;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.model.Videoconnect;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.utils.UserUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/videoconnects")
public class VideoconnectController {

	@Value("${videoconnect.path}")
	private String videoconnectPath;
    @Autowired
    private VideoconnectDao videoconnectDao;
    @Autowired
    private ExpertinfoDao expertinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Videoconnect save(@RequestBody Videoconnect videoconnect) {
        videoconnectDao.save(videoconnect);

        return videoconnect;
    }
    
    @GetMapping("/vc/{eid}")
    @ApiOperation(value = "发起视频会议")
    public Videoconnect saveVc(@PathVariable int eid) {
    	Expertinfo expertinfo = this.expertinfoDao.ByUid(eid);
    	if(expertinfo.getState() == 1) {
    		Videoconnect videoConnect =new Videoconnect();
        	User currentUser = UserUtil.getCurrentUser();
            videoConnect.setHid(Integer.parseInt(currentUser.getId().toString()));
            videoConnect.setEid(eid);
            videoConnect.setUrl(videoconnectPath);
            int round = (int) (Math.random() * 1000000);
            videoConnect.setRoomid(round);
            videoconnectDao.save(videoConnect);
            sendMsg(videoConnect);
            return videoConnect;
    	}else {
    		return null;
    	}
    	
    }
    @Autowired
	private ApplicationContext applicationContext;

	private void sendMsg(Videoconnect videoConnect) {
		applicationContext.publishEvent(new AdminEvent(videoConnect, EventType.NEW_VIDEO));
	}
    

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Videoconnect get(@PathVariable Long id) {
        return videoconnectDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Videoconnect update(@RequestBody Videoconnect videoconnect) {
        videoconnectDao.update(videoconnect);

        return videoconnect;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Videoconnect> list(TableRequest request) {
        return TableRequestHandler.<Videoconnect> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return videoconnectDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Videoconnect>() {

            @Override
            public List<Videoconnect> list(TableRequest request) {
                return videoconnectDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        videoconnectDao.delete(id);
    }
    
   
    
    
}
