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
import com.orhonit.admin.server.sys.dao.StudyVideoDao;
import com.orhonit.admin.server.sys.dao.CategoryDao;
import com.orhonit.admin.server.sys.model.StudyVideo;
import com.orhonit.admin.server.sys.dao.ExpertinfoDao;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/studyVideos")
public class StudyVideoController {

    @Autowired
    private StudyVideoDao studyVideoDao;
    
    @Autowired
    private ExpertinfoDao expertinfoDao;
    
    @Autowired
    private CategoryDao categoryDao;
    
    @PostMapping
    @ApiOperation(value = "保存")
    public StudyVideo save(@RequestBody StudyVideo studyVideo) {
        studyVideoDao.save(studyVideo);

        return studyVideo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public StudyVideo get(@PathVariable Long id) {
        return studyVideoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public StudyVideo update(@RequestBody StudyVideo studyVideo) {
        studyVideoDao.update(studyVideo);

        return studyVideo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public TableResponse<StudyVideo> list(TableRequest request) {
        return TableRequestHandler.<StudyVideo> builder().countHandler(new CountHandler() {

            @Override
            public int count(TableRequest request) {
                return studyVideoDao.count(request.getParams());
            }
        }).listHandler(new ListHandler<StudyVideo>() {

            @Override
            public List<StudyVideo> list(TableRequest request) {
               List<StudyVideo> studyVideoList= studyVideoDao.list(request.getParams(), request.getStart(), request.getLength());
               for(StudyVideo studyVideo:studyVideoList) {
            	   studyVideo.setName(expertinfoDao.ByUid(studyVideo.getUid()).getName()); 
            	   studyVideo.setCategoryName(categoryDao.getByParentId(studyVideo.getCategoryId()).getName());
               };
               return studyVideoList;
               
            }
        }).build().handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        studyVideoDao.delete(id);
    }
    
    @GetMapping("pass/{id}")
    @ApiOperation(value="文章审核通过")
    public int studyArticlePass(@PathVariable Long id) {
    			StudyVideo studyVideo = studyVideoDao.getById(id);
    			studyVideo.setStatus(1);
    		return studyVideoDao.updatePass(studyVideo);
    	
    }
    
    @GetMapping("fail/")
    @ApiOperation(value="文章审核失败")
    public int studyArticlePassFail(@RequestParam("id")Long id,@RequestParam("reason")String reason) {
    	StudyVideo studyVideo = studyVideoDao.getById(id);
    	studyVideo.setStatus(2);
    	studyVideo.setReason(reason);
    		return studyVideoDao.updateFail(studyVideo);
    	
    }
    
    
}
