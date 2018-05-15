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
import com.orhonit.admin.server.sys.model.Commentm;
import com.orhonit.admin.server.sys.service.CommentmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/commentms")
public class CommentmController {

    @Autowired
    private CommentmService commentmDaoService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Commentm save(@RequestBody Commentm commentm) {
    	commentmDaoService.save(commentm);

        return commentm;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Commentm get(@PathVariable Long id) {
        return commentmDaoService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Commentm update(@RequestBody Commentm commentm) {
    	commentmDaoService.update(commentm);

        return commentm;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<Commentm> list(TableRequest request) {
    	return commentmDaoService.list(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
    	commentmDaoService.delete(id);	
    }
    @GetMapping("/examine")
    @ApiOperation(value = "后台审核")
    public ResponseEntity<?> examine(Integer id){
    	return commentmDaoService.examine(id);
    }
    @PostMapping("/App/saveCom")
    @ApiOperation(value = "/App端发起文章评论汉语")
    public ResponseEntity<?> saveCom(Commentm commentm){
		return commentmDaoService.saveCom(commentm);
    }
    @GetMapping("/App/getList")
    @ApiOperation(value = "手机端拿到所有审核通过的")
    public ResponseEntity<?> AppGetList(){
    	return commentmDaoService.getList();
    }
}
