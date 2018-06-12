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
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import com.orhonit.admin.server.sys.service.DrugstoreinfoService;
import com.orhonit.admin.server.sys.utils.UserUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/drugstoreinfos")
public class DrugstoreinfoController {

    @Autowired
    private DrugstoreinfoService drugstoreinfoService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Drugstoreinfo save(@RequestBody Drugstoreinfo drugstoreinfo) {
    	drugstoreinfoService.save(drugstoreinfo);

        return drugstoreinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Drugstoreinfo get(@PathVariable Long id) {
        return drugstoreinfoService.getById(id);
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月24日
     * @return  
     * @Description: 手机端拿到药店详情信息 
     */
    @GetMapping("/AppGet")
    @ApiOperation(value = "app端拿到药店的详情信息")
    public ResponseEntity<?> AppGet(){
    	String id = UserUtil.getCurrentUser().getId().toString();
    	return drugstoreinfoService.AppGet(Integer.parseInt(id));
    }
    
    /**
     * @author: 孙少辉
     * @data: 2018年4月24日
     * @param drugstoreinfo
     * @return  
     * @Description: 手机端完善个人信息 
     */
    @PostMapping("/AppSave")
    @ApiOperation(value = "app端保存药店详情信息")
    public ResponseEntity<?> AppSave(Drugstoreinfo drugstoreinfo){
    	return drugstoreinfoService.AppSave(drugstoreinfo);
    }
    
    @PostMapping("/AppUpdate")
    @ApiOperation(value = "app端修改药店详情信息")
    public ResponseEntity<?> AppUpdate(Drugstoreinfo drugstoreinfo){
    	return drugstoreinfoService.AppUpdate(drugstoreinfo);
    }
    
    @GetMapping("drugstoreinfo/{id}")
    @ApiOperation(value = "根据uid获取")
    public Drugstoreinfo get(@PathVariable int id) {
        return drugstoreinfoService.getByUid(id);
    }
    
    @GetMapping("status/{id}")
    @ApiOperation(value = "获得所有审核通过的药店")
    public List<Drugstoreinfo> getStatus(@PathVariable int id) {
        return drugstoreinfoService.getByStatus(id);
    }

    @GetMapping("/passDrugstore/{id}")
    @ApiOperation(value="药店审核通过")
    public int getDrugstoreUid(@PathVariable Long id) {
    	return drugstoreinfoService.getDrugstoreUid(id);
    }
    
    @GetMapping("/failDrugstore/{id}/{pass}")
    @ApiOperation(value="药店审核失败")
    public int getDrugstoreFailUid(@PathVariable Long id,@PathVariable String pass) {
    	return drugstoreinfoService.getDrugstoreFailUid(id,pass);
	
    }
    
    @PutMapping
    @ApiOperation(value = "修改")
    public Drugstoreinfo update(@RequestBody Drugstoreinfo drugstoreinfo) {
    	drugstoreinfoService.update(drugstoreinfo);
        return drugstoreinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Drugstoreinfo> list(TableRequest request) {
    	return drugstoreinfoService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	drugstoreinfoService.delete(id);
    }
    
    /**
     * @author: 孙少辉
     * @data: 2018年4月26日
     * @param id
     * @return  
     * @Description: 根据诊断id和地区表对比拿到牧民附近的药店 蒙语
     */
    @GetMapping("/App/getByTaskIdM/{id}")
    @ApiOperation(value = "根据诊断id和地区表对比拿到牧民附近的药店")
    public ResponseEntity<?> getByTaskIdM(@PathVariable Integer id){
    	return drugstoreinfoService.getByTaskIdM(id);
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月26日
     * @param id
     * @return  
     * @Description: 根据诊断id和地区表对比拿到牧民附近的药店 汉语 
     */
    @GetMapping("/App/getByTaskId/{id}")
    @ApiOperation(value = "根据诊断id和地区表对比拿到牧民附近的药店")
    public ResponseEntity<?> getByTaskId(@PathVariable Integer id){
    	return drugstoreinfoService.getByTaskId(id);
    }
    
    @GetMapping("/App/getDrug/{id}")
    @ApiOperation(value = "根据药店id拿到药品")
    public ResponseEntity<?> getDrug(@PathVariable("id") Integer id){
    	return drugstoreinfoService.getDrug(id);
    }
    
}
