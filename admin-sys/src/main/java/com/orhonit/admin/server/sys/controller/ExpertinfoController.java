package com.orhonit.admin.server.sys.controller;



import java.util.List;

import org.apache.shiro.authc.UnknownAccountException;
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

import com.alibaba.fastjson.JSON;
import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.model.Prescription;
import com.orhonit.admin.server.sys.model.Prescriptionm;
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.service.ExpertinfoService;
import com.orhonit.admin.server.sys.utils.UserUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/expertinfos")
public class ExpertinfoController {

    @Autowired
    private ExpertinfoService expertinfoService;
  
    
    @PostMapping
    @ApiOperation(value = "保存")
    public Expertinfo save(@RequestBody Expertinfo expertinfo) {
    	expertinfoService.save(expertinfo);

        return expertinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Expertinfo get(@PathVariable Long id) {
        return expertinfoService.getById(id);
    }
    
    @GetMapping("expertinfo/{id}")
    @ApiOperation(value = "根据uid获取")
    public Expertinfo get(@PathVariable int id) {
        return expertinfoService.ByUid(id);
    }
    
    /**
     * @author: 孙少辉
     * @data: 2018年4月21日
     * @param id
     * @return  
     * @Description: 手机端根据uid拿到专家的详情信息，不存在返回401 
     */
    @GetMapping("/AppGet")
    @ApiOperation(value = "手机端根据uid获取")
    public ResponseEntity<?> getByUid(){
    	return expertinfoService.getByUid(Integer.parseInt(UserUtil.getCurrentUser().getId().toString()));
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月23日
     * @param expertinfo
     * @return  
     * @Description: 专家保存个人资料 
     */
    @PostMapping("/AppPut")
    @ApiOperation(value = "手机端完善专家个人资料")
    public ResponseEntity<?> saveExpertinfo(Expertinfo expertinfo){
    	return expertinfoService.saveExpertinfo(expertinfo);
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月23日
     * @param expertinfo
     * @return  
     * @Description: APP端修改专家信息 
     */
    @PostMapping("/AppUpdate")
    @ApiOperation(value = "手机端修改专家个人资料")
    public ResponseEntity<?> AppUpdate(Expertinfo expertinfo){
    	return expertinfoService.AppUpdate(expertinfo);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Expertinfo update(@RequestBody Expertinfo expertinfo) {
    	expertinfoService.update(expertinfo);
        return expertinfo;
    }

    @GetMapping("/passExpert/{id}")
    @ApiOperation(value="专家审核通过")
    public int getUid(@PathVariable Long id) {
    	return expertinfoService.getUid(id);
    }
    
    @GetMapping("/failExpert/{id}/{pass}")
    @ApiOperation(value="专家审核失败")
    public int getFailUid(@PathVariable Long id,@PathVariable String pass) {
    	return expertinfoService.getFailUid(id,pass);
    }
    
    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Expertinfo> list(TableRequest request) {
    	return expertinfoService.list(request);
    }
    @GetMapping("/AppList")
    @ApiOperation(value = "App端拿到所有审核通过的专家")
    public ResponseEntity<?> AppList(){
    	return expertinfoService.AppList();
    }
    
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	expertinfoService.delete(id);
    }
    
    @GetMapping("/createTask/{id}")
    @ApiOperation(value = "专家视频完毕创建诊断表")
    public void createTask(@PathVariable Long id){
    	try {
			expertinfoService.createTask(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnknownAccountException("系统错误");
		}
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月26日
     * @param taskm
     * @param json  
     * @Description: 专家完善蒙语病例并开药 
     */
    @PostMapping("/App/updateTaskm")
    @ApiOperation(value = "专家提交完善的蒙语病例并开药")
    public ResponseEntity<?> examinem(Taskm taskm,String json){
    	try {
			System.out.println(taskm);
			System.err.println(json);
			List<Prescriptionm> list = JSON.parseArray(json, Prescriptionm.class);
			for (Prescriptionm prescriptionm : list) {
				System.out.println(prescriptionm);
			}
			expertinfoService.examinem(taskm,list);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(401).body("错误");
		}
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月26日
     * @param task
     * @param json
     * @return  
     * @Description: 专家提交完善的汉语病例并开药 
     */
    @PostMapping("/App/updateTask")
    @ApiOperation(value = "专家提交完善的汉语病例并开药")
    public ResponseEntity<?> examine(Task task,String json){
    	try {
    		System.out.println(task);
    		System.err.println(json);
    		List<Prescription> list = JSON.parseArray(json, Prescription.class);
    		for (Prescription prescription : list) {
    			System.out.println(prescription);
    		}
    		expertinfoService.examine(task,list);
    		return ResponseEntity.ok(null);
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		return ResponseEntity.status(401).body("错误");
    	}
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月26日
     * @param phone
     * @return  
     * @Description: 专家根据牧民电话号添加汉语病例 
     */
    @GetMapping("/App/CreateTaskByPhone/{phone}")
    @ApiOperation(value = "专家根据牧民电话号添加病例")
    public ResponseEntity<?> CreateTaskByPhone(@PathVariable String phone){
    	return expertinfoService.CreateByPhone(phone);
    }
    /**
     * @author: 孙少辉
     * @data: 2018年4月26日
     * @param phone
     * @return  
     * @Description: 专家根据牧民电话号添加蒙语病例 
     */
    @GetMapping("/App/CreateTaskmByPhone/{phone}")
    @ApiOperation(value = "专家根据牧民电话号添加病例")
    public ResponseEntity<?> CreateTaskmByPhone(@PathVariable String phone){
    	return expertinfoService.CreatemByPhone(phone);
    }
}
