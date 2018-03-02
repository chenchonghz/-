package com.orhonit.admin.server.sys.controller;

import java.util.List;

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
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.dao.StudyArticleDao;
import com.orhonit.admin.server.sys.dao.CategoryDao;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;
import com.orhonit.admin.server.sys.model.StudyArticle;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/studyArticles")
public class StudyArticleController {

    @Autowired
    private StudyArticleDao studyArticleDao;
    
    @Autowired
    private ExpertinfoDao expertinfoDao;
    
    @Autowired
    private CategoryDao categoryDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public StudyArticle save(@RequestBody StudyArticle studyArticle) {
        studyArticleDao.save(studyArticle);

        return studyArticle;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public StudyArticle get(@PathVariable Long id) {
        return studyArticleDao.getId(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public StudyArticle update(@RequestBody StudyArticle studyArticle) {
        studyArticleDao.update(studyArticle);

        return studyArticle;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<StudyArticle> list(TableRequest request) {
        return TableRequestHandler.<StudyArticle> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return studyArticleDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<StudyArticle>() {

            @Override
            public List<StudyArticle> list(TableRequest request) {
            	List<StudyArticle> studyArticleList=studyArticleDao.list(request.getParams(), request.getStart(), request.getLength());
            		for(StudyArticle studyArticle : studyArticleList) {
            		studyArticle.setName(expertinfoDao.ByUid(studyArticle.getUid()).getName()); 
            		studyArticle.setCategoryName(categoryDao.getByParentId(studyArticle.getCategoryId()).getName());	
            		}
            		return studyArticleList;
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        studyArticleDao.delete(id);
    }
    
    @GetMapping("pass/{id}")
    @ApiOperation(value="文章审核通过")
    public int studyArticlePass(@PathVariable Long id) {
    			StudyArticle studyArticle = studyArticleDao.getById(id);
    			studyArticle.setStatus(1);
    		return studyArticleDao.updatePass(studyArticle);
    	
    }
    
    @GetMapping("fail/")
    @ApiOperation(value="文章审核失败")
    public int studyArticlePassFail(@RequestParam("id")Long id,@RequestParam("reason")String reason) {
    			StudyArticle studyArticle = studyArticleDao.getById(id);
    			studyArticle.setStatus(2);
    			studyArticle.setReason(reason);
    		return studyArticleDao.updateFail(studyArticle);
    	
    }
    
    
    
    
}
