package com.orhonit.admin.server.app.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.exceptions.ClientException;
import com.orhonit.admin.server.common.utils.RandomCode;
import com.orhonit.admin.server.common.utils.SMSutil;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.model.Medicalequipment;
import com.orhonit.admin.server.sys.model.StudyArticle;
import com.orhonit.admin.server.sys.model.StudyVideo;
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.service.DrugstoreinfoService;
import com.orhonit.admin.server.sys.service.ExpertinfoService;
import com.orhonit.admin.server.sys.service.MedicalequipmentSerivce;
import com.orhonit.admin.server.sys.service.StudyArticleService;
import com.orhonit.admin.server.sys.service.StudyVideoService;
import com.orhonit.admin.server.sys.service.TaskService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app")
public class CommonAppController {
	@Autowired
    private ExpertinfoService expertinfoService;
	@GetMapping("/expertinfo/three")
    @ApiOperation(value="诊断前三")
    public List<Expertinfo> three(){
 	   return expertinfoService.three();
    }
	@GetMapping("/expertinfo/ten/{start}")
	@ApiOperation(value="专家的十条数据")
	public List<Expertinfo> Eten(@PathVariable Long start){
		return expertinfoService.ten(start-1);
	}
	
	@Autowired
    private MedicalequipmentSerivce medicalequipmentService;
	@GetMapping("/medicalequipment/all")
    @ApiOperation(value = "查询所有")
    public List<Medicalequipment> all(){
    	return medicalequipmentService.all();
    }
	
	@Autowired
    private DrugstoreinfoService drugstoreinfoService;
	@GetMapping("/drugstoreinfo/ten/{start}")
    @ApiOperation(value = "药店的10条数据")
    public List<Drugstoreinfo> Dten(@PathVariable Long start){
    	return drugstoreinfoService.ten(start-1);
    }
	
	@Autowired
    private StudyVideoService studyVideoService;
	@GetMapping("/studyvideo/frist")
	@ApiOperation("观看最多的1条视频学习动物1条数据")
	public StudyVideo frist(){
		return studyVideoService.frist();
	}
	@GetMapping("/studyvideo/ten/{start}")
	@ApiOperation(value="视频学习的十条数据")
	public List<StudyVideo> Sten(@PathVariable Long start){
		return studyVideoService.ten(start - 1);
	}
	
	@Autowired
    private StudyArticleService studyArticleService;
	@GetMapping("/studyarticle/ten/{start}")
	@ApiOperation(value="文章资料学习的十条数据")
	public List<StudyArticle> SAten(@PathVariable Long start){
		return studyArticleService.ten(start-1);
	}
	
	@Autowired
    private TaskService taskService;
	@GetMapping("/task/ten/{start}")
	@ApiOperation(value="动物病例十条数据")
	public List<Task> Tten(@PathVariable Long start){
		return taskService.ten(start - 1);
	}
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private static final String PHONE_PREFIX = "Phones:";
	@GetMapping("/sendMsg/{phone}")
	@ApiOperation(value="发送验证码")
	public String sendMsg(@PathVariable String phone){
		String code =String.valueOf(RandomCode.genCode());
		redisTemplate.opsForValue().set(PHONE_PREFIX + phone, code, 300, TimeUnit.SECONDS);
		try {
			SMSutil.sendSms(phone, code);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "验证码发送失败";
		}
		return "验证码发送成功";
	}
	@GetMapping("/getMsg/{phone}")
	@ApiOperation(value="获取验证码")
	public String getMsg(@PathVariable String phone){
		String code = redisTemplate.opsForValue().get(phone);
		return code;
	}
	
}
