package com.orhonit.admin.server.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.orhonit.admin.server.sys.model.StudyArticle;
import com.orhonit.admin.server.sys.service.StudyArticleService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/studyArticles")
public class StudyArticleController {

    @Autowired
    private StudyArticleService studyArticleService;

    @PostMapping
    @ApiOperation(value = "保存")
    public StudyArticle save(@RequestBody StudyArticle studyArticle) {
    	studyArticleService.save(studyArticle);

        return studyArticle;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public StudyArticle get(@PathVariable Long id) {
        return studyArticleService.getId(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public StudyArticle update(@RequestBody StudyArticle studyArticle) {
    	studyArticleService.update(studyArticle);

        return studyArticle;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<StudyArticle> list(TableRequest request) {
    	return studyArticleService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	studyArticleService.delete(id);
    }
    
    @GetMapping("pass/{id}")
    @ApiOperation(value="文章审核通过")
    public int studyArticmlePass(@PathVariable Long id) {
    	return studyArticleService.studyArticlePass(id);
    }
    
    @GetMapping("fail/")
    @ApiOperation(value="文章审核失败")
    public int studyArticlemPassFail(@RequestParam("id")Long id,@RequestParam("reason")String reason) {
    	return studyArticleService.studyArticlePassFail(id,reason);
    }
    
    @PostMapping("/App/add")
    @ApiOperation(value = "手机端专家添加文章")
    public ResponseEntity<?> AppAdd(StudyArticle studyArticle){
    	return studyArticleService.AppAdd(studyArticle);
    }
    @GetMapping("/App/getAll")
    @ApiOperation(value = "文章列表")
    public ResponseEntity<?> getAll(){
    	return studyArticleService.getAll();
    }
    @GetMapping("/App/getByUid")
    @ApiOperation(value = "文章列表")
    public ResponseEntity<?> getByUid(){
    	return studyArticleService.getByUid();
    }
    
}
