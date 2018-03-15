package com.orhonit.admin.server.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orhonit.admin.server.common.annotation.LogAnnotation;
import com.orhonit.admin.server.common.constants.EventType;
import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.common.event.AdminEvent;
import com.orhonit.admin.server.sys.model.Articles;
import com.orhonit.admin.server.sys.model.Articles.Status;
import com.orhonit.admin.server.sys.service.ArticlesService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

	@Autowired
	private ArticlesService articlesService;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存文章")
	@RequiresPermissions("articles:add")
	public Articles saveArticles(@RequestBody Articles articles) {
		articlesService.save(articles);
		sendMsg(articles);
		return articles;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取文章")
	@RequiresPermissions("articles:query")
	public Articles get(@PathVariable Long id) {
		return articlesService.getById(id);
	}

	@Autowired
	private ApplicationContext applicationContext;

	private void sendMsg(Articles articles) {
		if (articles.getStatus() == Status.PUBLISH) {// 发公告通知，过滤自己
			applicationContext.publishEvent(new AdminEvent(articles, EventType.NEW_NOTICE));
		}
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改文章")
	@RequiresPermissions("articles:add")
	public Articles updateArticles(@RequestBody Articles articles) {
		articlesService.update(articles);
		sendMsg(articles);

		return articles;
	}

	@GetMapping
	@ApiOperation(value = "文章列表")
	@RequiresPermissions("articles:query")
	public TableResponse<Articles> listArticles(TableRequest request) {
		return articlesService.listArticles(request);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除文章")
	@RequiresPermissions(value = { "articles:del" })
	public void delete(@PathVariable Long id) {
		articlesService.delete(id);
	}
}
