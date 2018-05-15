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
import com.orhonit.admin.server.sys.model.Comment;
import com.orhonit.admin.server.sys.service.CommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Comment save(@RequestBody Comment comment) {
    	commentService.save(comment);

        return comment;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Comment get(@PathVariable Long id) {
        return commentService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Comment update(@RequestBody Comment comment) {
    	commentService.update(comment);

        return comment;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Comment> list(TableRequest request) {
    	return commentService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	commentService.delete(id);
    }
    @GetMapping("/examine")
    @ApiOperation(value = "后台审核")
    public ResponseEntity<?> examine(Integer id){
    	return commentService.examine(id);
    }
    
    @PostMapping("/App/saveCom")
    @ApiOperation(value = "/App端发起文章评论汉语")
    public ResponseEntity<?> saveCom(Comment comment){
		return commentService.saveCom(comment);
    }
    @GetMapping("/App/getList")
    @ApiOperation(value = "手机端拿到所有审核通过的")
    public ResponseEntity<?> AppGetList(){
    	return commentService.getList();
    }
}
