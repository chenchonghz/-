package com.orhonit.admin.server.sys.controller;

import java.util.List;

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
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.model.Expertinfo;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/expertinfos")
public class ExpertinfoController {

    @Autowired
    private ExpertinfoDao expertinfoDao;
  
    
    @PostMapping
    @ApiOperation(value = "保存")
    public Expertinfo save(@RequestBody Expertinfo expertinfo) {
        expertinfoDao.save(expertinfo);

        return expertinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Expertinfo get(@PathVariable Long id) {
        return expertinfoDao.getById(id);
    }
    
    @GetMapping("expertinfo/{id}")
    @ApiOperation(value = "根据uid获取")
    public Expertinfo get(@PathVariable int id) {
        return expertinfoDao.ByUid(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Expertinfo update(@RequestBody Expertinfo expertinfo) {
    	
        expertinfoDao.update(expertinfo);

        return expertinfo;
    }

    @GetMapping("/passExpert/{id}")
    @ApiOperation(value="专家审核通过")
    public int getUid(@PathVariable Long id) {
    	Expertinfo expert=expertinfoDao.ById(id);
    	expert.setStatus(1);
    	return expertinfoDao.update(expert);
	
    }
    
    @GetMapping("/failExpert/{id}")
    @ApiOperation(value="专家审核失败")
    public int getFailUid(@PathVariable Long id) {
    	Expertinfo expert=expertinfoDao.ById(id);
    	expert.setStatus(2);
    	return expertinfoDao.update(expert);
	
    }
    
    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Expertinfo> list(TableRequest request) {
        return TableRequestHandler.<Expertinfo> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return expertinfoDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<Expertinfo>() {

            @Override
            public List<Expertinfo> list(TableRequest request) {
                return expertinfoDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        expertinfoDao.delete(id);
    }
    
   
    
}
