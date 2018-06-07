package com.orhonit.admin.server.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Studyvideom;
import com.orhonit.admin.server.sys.service.StudyvideomService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/studyvideoms")
public class StudyvideomController {

    @Autowired
    private StudyvideomService studyvideomService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Studyvideom save(@RequestBody Studyvideom studyvideom) {
    	studyvideomService.save(studyvideom);

        return studyvideom;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Studyvideom get(@PathVariable Long id) {
        return studyvideomService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Studyvideom update(@RequestBody Studyvideom studyvideom) {
    	studyvideomService.update(studyvideom);

        return studyvideom;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Studyvideom> list(TableRequest request) {
    	return studyvideomService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	studyvideomService.delete(id);
    }
    
    @GetMapping("pass/{id}")
    @ApiOperation(value="文章审核通过")
    public int studyArticlePass(@PathVariable Long id) {
    	return studyvideomService.studyArticlePass(id);
    }
    
    @GetMapping("fail/")
    @ApiOperation(value="文章审核失败")
    public int studyArticlePassFail(@RequestParam("id")Long id,@RequestParam("reason")String reason) {
    	return studyvideomService.studyArticlePassFail(id,reason);
    }
    
    
}
