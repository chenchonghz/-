package com.orhonit.admin.server.sys.controller;

import java.util.List;

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
import com.orhonit.admin.server.sys.model.Districtm;
import com.orhonit.admin.server.sys.service.DistrictmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/districtms")
public class DistrictmController {

    @Autowired
    private DistrictmService districtmService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Districtm save(@RequestBody Districtm districtm) {
    	districtmService.save(districtm);

        return districtm;
    }
    @GetMapping("/province")
    @ApiOperation(value = "获取省级名称")
    public List<Districtm> province(){
    	
    	List<Districtm> byUpid = districtmService.getByUpid(0);
    	return byUpid;
    }
    
    @GetMapping("/city/{id}")
    @ApiOperation(value = "获取市级名称")
    public List<Districtm> city(@PathVariable int id){
    	
    	List<Districtm> byUpid = districtmService.getByUpid(id);
    	return byUpid;
    }
    
    @GetMapping("/area/{id}")
    @ApiOperation(value = "获取区或县级名称")
    public List<Districtm> area(@PathVariable int id){
    	
    	List<Districtm> byUpid = districtmService.getByUpid(id);
    	return byUpid;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Districtm get(@PathVariable Long id) {
        return districtmService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Districtm update(@RequestBody Districtm districtm) {
    	districtmService.update(districtm);

        return districtm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Districtm> list(TableRequest request) {
    	return districtmService.list(request);
       
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	districtmService.delete(id);
    }
    @GetMapping("App/getAll")
	@ApiOperation(value = "App端拿到所有数据")
	public ResponseEntity<?> getAll(){
		return districtmService.getAll();
	}
}
