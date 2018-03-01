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
import com.orhonit.admin.server.sys.dao.DistrictDao;
import com.orhonit.admin.server.sys.model.District;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    @Autowired
    private DistrictDao districtDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public District save(@RequestBody District district) {
        districtDao.save(district);

        return district;
    }
    @GetMapping("/province")
    @ApiOperation(value = "获取省级名称")
    public List<District> province(){
    	
    	List<District> byUpid = districtDao.getByUpid(0);
    	return byUpid;
    }
    
    @GetMapping("/city/{id}")
    @ApiOperation(value = "获取市级名称")
    public List<District> city(@PathVariable int id){
    	
    	List<District> byUpid = districtDao.getByUpid(id);
    	return byUpid;
    }
    
    @GetMapping("/area/{id}")
    @ApiOperation(value = "获取区或县级名称")
    public List<District> area(@PathVariable int id){
    	
    	List<District> byUpid = districtDao.getByUpid(id);
    	return byUpid;
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public District get(@PathVariable Long id) {
        return districtDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public District update(@RequestBody District district) {
        districtDao.update(district);

        return district;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<District> list(TableRequest request) {
        return TableRequestHandler.<District> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return districtDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<District>() {

            @Override
            public List<District> list(TableRequest request) {
                return districtDao.list(request.getParams(), request.getStart(), request.getLength());
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        districtDao.delete(id);
    }
}
