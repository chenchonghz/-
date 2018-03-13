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
import com.orhonit.admin.server.sys.model.StudyVideo;
import com.orhonit.admin.server.sys.service.StudyVideoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/studyVideos")
public class StudyVideoController {

    @Autowired
    private StudyVideoService studyVideoService;
    
    @PostMapping
    @ApiOperation(value = "保存")
    public StudyVideo save(@RequestBody StudyVideo studyVideo) {
    	studyVideoService.save(studyVideo);
        return studyVideo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public StudyVideo get(@PathVariable Long id) {
        return studyVideoService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public StudyVideo update(@RequestBody StudyVideo studyVideo) {
    	studyVideoService.update(studyVideo);
        return studyVideo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<StudyVideo> list(TableRequest request) {
    	return studyVideoService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	studyVideoService.delete(id);
    }
    
    @GetMapping("pass/{id}")
    @ApiOperation(value="文章审核通过")
    public int studyArticlePass(@PathVariable Long id) {
    	return studyVideoService.studyArticlePass(id);
    }
    
    @GetMapping("fail/")
    @ApiOperation(value="文章审核失败")
    public int studyArticlePassFail(@RequestParam("id")Long id,@RequestParam("reason")String reason) {
    	return studyVideoService.studyArticlePassFail(id,reason);
    }
    
    
}
