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
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.model.Feedback;
import com.orhonit.admin.server.sys.service.FeedbackService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Feedback save(@RequestBody Feedback feedback) {
    	feedbackService.save(feedback);
        return feedback;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Feedback get(@PathVariable Long id) {
        return feedbackService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Feedback update(@RequestBody Feedback feedback) {
    	feedbackService.update(feedback);
        return feedback;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Feedback> list(TableRequest request) {
    	return feedbackService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	feedbackService.delete(id);
    }
    
    @PostMapping("/App/save")
    @ApiOperation(value = "手机端添加评论意见")
    public ResponseEntity<?> Appsave(Feedback feedback){
    	return feedbackService.Appsave(feedback);
    }
}
