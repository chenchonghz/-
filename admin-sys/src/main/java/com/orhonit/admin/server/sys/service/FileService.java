package com.orhonit.admin.server.sys.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.orhonit.admin.server.sys.model.FileInfo;

public interface FileService {

	FileInfo save(MultipartFile file) throws IOException;

	void delete(String id);

}
