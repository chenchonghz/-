package com.orhonit.admin.server.generate.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.annotation.LogAnnotation;
import com.orhonit.admin.server.generate.model.BeanField;
import com.orhonit.admin.server.generate.model.GenerateDetail;
import com.orhonit.admin.server.generate.model.GenerateInput;
import com.orhonit.admin.server.generate.service.GenerateService;

/**
 * 代码生成接口
 * 
 * @author caodw
 *
 */
@RestController
@RequestMapping("/generate")
public class GenerateController {

	@Autowired
	private GenerateService generateService;

	@GetMapping(params = { "tableName" })
	@RequiresPermissions("generate:edit")
	public GenerateDetail generateByTableName(String tableName) {
		GenerateDetail detail = new GenerateDetail();
		detail.setBeanName(generateService.upperFirstChar(tableName));
		List<BeanField> fields = generateService.listBeanField(tableName);
		detail.setFields(fields);

		return detail;
	}

	@LogAnnotation(module = "生成代码")
	@PostMapping
	@RequiresPermissions("generate:edit")
	public void save(@RequestBody GenerateInput input) {
		generateService.saveCode(input);
	}

}
