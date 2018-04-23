package com.orhonit.admin.server.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.exceptions.ClientException;
import com.orhonit.admin.server.common.utils.RandomCode;
import com.orhonit.admin.server.common.utils.SMSutil;
import com.orhonit.admin.server.sys.dto.UserDto;
import com.orhonit.admin.server.sys.model.Drug;
import com.orhonit.admin.server.sys.model.Drugstoreinfo;
import com.orhonit.admin.server.sys.model.Expertinfo;
import com.orhonit.admin.server.sys.model.Medicalequipment;
import com.orhonit.admin.server.sys.model.Medicalequipmentm;
import com.orhonit.admin.server.sys.model.Newherdsman;
import com.orhonit.admin.server.sys.model.Slide;
import com.orhonit.admin.server.sys.model.StudyArticle;
import com.orhonit.admin.server.sys.model.StudyVideo;
import com.orhonit.admin.server.sys.model.Studyarticlem;
import com.orhonit.admin.server.sys.model.Studyvideom;
import com.orhonit.admin.server.sys.model.Task;
import com.orhonit.admin.server.sys.model.Taskm;
import com.orhonit.admin.server.sys.model.User;
import com.orhonit.admin.server.sys.service.DrugService;
import com.orhonit.admin.server.sys.service.DrugstoreinfoService;
import com.orhonit.admin.server.sys.service.ExpertinfoService;
import com.orhonit.admin.server.sys.service.MedicalequipmentSerivce;
import com.orhonit.admin.server.sys.service.MedicalequipmentmService;
import com.orhonit.admin.server.sys.service.NewherdsmanService;
import com.orhonit.admin.server.sys.service.SlideService;
import com.orhonit.admin.server.sys.service.SlidemService;
import com.orhonit.admin.server.sys.service.StudyArticleService;
import com.orhonit.admin.server.sys.service.StudyVideoService;
import com.orhonit.admin.server.sys.service.StudyarticlemService;
import com.orhonit.admin.server.sys.service.StudyvideomService;
import com.orhonit.admin.server.sys.service.TaskService;
import com.orhonit.admin.server.sys.service.TaskmService;
import com.orhonit.admin.server.sys.service.UserService;

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
	private MedicalequipmentmService MedicalequipmentmService;
	@GetMapping("/medicalequipmentm/all")
	@ApiOperation(value = "查询蒙语所有")
	public List<Medicalequipmentm> allm(){
		return MedicalequipmentmService.all();
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
	@ApiOperation("观看最多的1条视频学习")
	public StudyVideo frist(){
		return studyVideoService.frist();
	}
	@GetMapping("/studyvideo/ten/{start}")
	@ApiOperation(value="视频学习的十条数据")
	public List<StudyVideo> Sten(@PathVariable Long start){
		return studyVideoService.ten(start - 1);
	}
	@Autowired
	private StudyvideomService studyvideomService;
	@GetMapping("/studyvideom/frist")
	@ApiOperation("蒙语观看最多的1条视频学习")
	public Studyvideom fristm(){
		return studyvideomService.frist();
	}
	@GetMapping("/studyvideom/ten/{start}")
	@ApiOperation(value="蒙语视频学习的十条数据")
	public List<Studyvideom> Stenm(@PathVariable Long start){
		return studyvideomService.ten(start - 1);
	}
	
	@Autowired
    private StudyArticleService studyArticleService;
	@GetMapping("/studyarticle/ten/{start}")
	@ApiOperation(value="文章资料学习的十条数据")
	public List<StudyArticle> SAten(@PathVariable Long start){
		return studyArticleService.ten(start-1);
	}
	@Autowired
	private StudyarticlemService studyarticlemService;
	@GetMapping("/studyarticlem/ten/{start}")
	@ApiOperation(value="蒙语文章资料学习的十条数据")
	public List<Studyarticlem> SAtenm(@PathVariable Long start){
		return studyarticlemService.ten(start-1);
	}
	
	@Autowired
    private TaskService taskService;
	@GetMapping("/task/ten/{start}")
	@ApiOperation(value="动物病例十条数据")
	public List<Task> Tten(@PathVariable Long start){
		return taskService.ten(start - 1);
	}
	@Autowired
	private TaskmService taskmService;
	@GetMapping("/taskm/ten/{start}")
	@ApiOperation(value="蒙语动物病例十条数据")
	public List<Taskm> Ttenm(@PathVariable Long start){
		return taskmService.ten(start - 1);
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
		String code = redisTemplate.opsForValue().get(PHONE_PREFIX + phone);
		return code;
	}
	@Autowired
	private UserService userService;
	@PostMapping("checkMsg")
	@ApiOperation(value = "验证验证码注册用户")
	public User checkMsg(String username,String pass,String code,String type,String rid){
		String phone = redisTemplate.opsForValue().get(PHONE_PREFIX + username);
		if(phone == null){
			throw new UnknownAccountException("验证码已过期");
		}
		if(!phone.equals(code)){
			throw new IncorrectCredentialsException("验证码有误");
		}
		UserDto userDto = new UserDto();
		userDto.setUsername(username);
		userDto.setPassword(pass);
		userDto.setType(Integer.parseInt(type));
		ArrayList<Long> list = new ArrayList<>();
		list.add(Long.parseLong(rid));
		userDto.setRoleIds(list);
		User u = userService.getUser(userDto.getUsername());
		if (u != null) {
			throw new IllegalArgumentException(userDto.getUsername() + "已存在");
		}

		return userService.saveUser(userDto);
	}
	
	@Autowired
	private DrugService drugService;
	@GetMapping("/getDrug/{uid}")
	@ApiOperation(value="根据药店的uid获得药品的信息")
	public List<Drug> getDrug(@PathVariable Long uid){
		return drugService.getByUid(uid);
	}
	
	@Autowired
	private NewherdsmanService newherdsmanService;
	@GetMapping("/getnewherdsman/{uid}")
	@ApiOperation(value="根据牧民的uid获得信息")
	public Newherdsman getnewherdsman(@PathVariable Long uid){
		return newherdsmanService.getUid(uid);
	}
	
	@Autowired
	private SlideService slideService;
	@GetMapping("/getSlideList")
	@ApiOperation(value = "拿到汉语轮播图列表")
	public List<Slide> getSlideList(){
		return slideService.getSlideList();
	}
	@Autowired
	private SlidemService slidemService;
	@GetMapping("/getSlidemList")
	@ApiOperation(value = "拿到蒙语轮播图列表")
	public List<Slide> getSlidemList(){
		return slidemService.getSlidemList();
	}
}
