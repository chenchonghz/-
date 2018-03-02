package com.orhonit.admin.server.sys.controller;

import java.io.IOException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.orhonit.admin.server.common.annotation.LogAnnotation;
import com.orhonit.admin.server.common.datatables.TableRequest;
import com.orhonit.admin.server.common.datatables.TableRequestHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.CountHandler;
import com.orhonit.admin.server.common.datatables.TableRequestHandler.ListHandler;
import com.orhonit.admin.server.common.datatables.TableResponse;
import com.orhonit.admin.server.sys.dao.FileInfoDao;
import com.orhonit.admin.server.sys.dto.LayuiFile;
import com.orhonit.admin.server.sys.dto.LayuiFile.LayuiFileData;
import com.orhonit.admin.server.sys.model.FileInfo;
import com.orhonit.admin.server.sys.service.FileService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;
	@Autowired
	private FileInfoDao fileInfoDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "文件上传")
	public FileInfo uploadFile(MultipartFile file) throws IOException {
		return fileService.save(file);
	}

	/**
	 * layui富文本文件自定义上传
	 * 
	 * @param file
	 * @param domain
	 * @return
	 * @throws IOException
	 */
	@LogAnnotation
	@PostMapping("/layui")
	@ApiOperation(value = "layui富文本文件自定义上传")
	public LayuiFile uploadLayuiFile(MultipartFile file, String domain) throws IOException {
		FileInfo fileInfo = fileService.save(file);

		LayuiFile layuiFile = new LayuiFile();
		layuiFile.setCode(0);
		LayuiFileData data = new LayuiFileData();
		layuiFile.setData(data);
		data.setSrc(domain + "/files" + fileInfo.getUrl());
		data.setTitle(file.getOriginalFilename());

		return layuiFile;
	}

	@GetMapping
	@ApiOperation(value = "文件查询")
	@RequiresPermissions("sys:file:query")
	public TableResponse<FileInfo> listFiles(TableRequest request) {
		return TableRequestHandler.<FileInfo> builder().countHandler(new CountHandler() {

			@Override
			public int count(TableRequest request) {
				return fileInfoDao.count(request.getParams());
			}
		}).listHandler(new ListHandler<FileInfo>() {

			@Override
			public List<FileInfo> list(TableRequest request) {
				List<FileInfo> list = fileInfoDao.list(request.getParams(), request.getStart(), request.getLength());
				return list;
			}
		}).build().handle(request);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "文件删除")
	@RequiresPermissions("sys:file:del")
	public void delete(@PathVariable String id) {
		fileService.delete(id);
	}

}
