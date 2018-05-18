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
import com.orhonit.admin.server.sys.model.Drug;
import com.orhonit.admin.server.sys.service.DrugService;
import com.orhonit.admin.server.sys.utils.UserUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/drugs")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Drug save(@RequestBody Drug drug) {
    	drugService.save(drug);

        return drug;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Drug get(@PathVariable Long id) {
        return drugService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Drug update(@RequestBody Drug drug) {
    	drugService.update(drug);

        return drug;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Drug> list(TableRequest request) {
    	return drugService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	drugService.delete(id);
    }
    @GetMapping("App/getByDid")
    @ApiOperation(value = "根据药店Id拿到上架的药")
    public ResponseEntity<?> getByDid(Integer Did){
    	return drugService.getByDid(Did);
    }
    @PostMapping("App/add")
    @ApiOperation(value = "手机端增加药品")
    public ResponseEntity<?> AppAdd(Drug drug){
    	return drugService.AppAdd(drug);
    }
    @GetMapping("App/getByUid")
    @ApiOperation(value = "药店查看自己的药品")
    public ResponseEntity<?> getByUid(){
    	Long id = UserUtil.getCurrentUser().getId();
    	List<Drug> list = drugService.getByUid(id);
    	return ResponseEntity.ok(list);
    }
    @GetMapping("App/delete")
    @ApiOperation("手机端根据id删除")
    public ResponseEntity<?> Appdelete(Long id){
    	try {
			drugService.delete(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
    }
    @GetMapping("/App/updateStatus")
    @ApiOperation("手机端修改上下架状态")
    public ResponseEntity<?> updateStatus(Integer id, Integer status){
    	return drugService.updateStatus(id,status);
    }
}
