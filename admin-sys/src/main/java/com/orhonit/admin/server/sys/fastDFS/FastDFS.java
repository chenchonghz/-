package com.orhonit.admin.server.sys.fastDFS;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fastDFS")
public class FastDFS {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	 @PostMapping(produces = MediaType.TEXT_HTML_VALUE)
	 @ApiOperation(value = "上传")
	 public String upload(MultipartFile file) throws Exception{
		boolean flag = false;
		FastDFSEntity fastDFSEntity = new FastDFSEntity();
		 // 校验后缀
		String oname = file.getOriginalFilename();
		// 如果后缀是要求的格式结尾，标志位设置为true，跳出寻汗
		if(StringUtils.endsWithIgnoreCase(oname, "mp4")){
			flag = true;
		}
		// 如果校验失败，直接返回
		fastDFSEntity.setState(0);
		if(!flag){
			fastDFSEntity.setUrl("文件必须为MP4格式");
			String json = MAPPER.writeValueAsString(fastDFSEntity);
			return json;
		}
		 
		 // 1. 加载tracker配置文件
		ClientGlobal.init(System.getProperty("user.dir")+"/src/main/resources/resource/tracker.conf");
		// 2. 创建TrackerClient
		TrackerClient trackerClient = new TrackerClient();
		// 3. 获取TrackerServer
		TrackerServer trackerServer = trackerClient.getConnection();
		// 4. 声明StorageServer，为null
		StorageServer storageServer = null;
		// 5. 创建StorageClient
		StorageClient storageClient = new StorageClient(trackerServer,storageServer);
		// 6. 使用StorageClient
		// 获取上传文件的后缀名
		System.out.println(file.getOriginalFilename());
		String ext = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
		String[] str;
		try {
			str = storageClient.upload_file(file.getBytes(),ext, null);
			System.err.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fastDFSEntity.setUrl("文件太大，请上传少于1G文件");
			String json = MAPPER.writeValueAsString(fastDFSEntity);
			return json;
		}
		System.err.println(str);
		if(str != null){
			fastDFSEntity.setState(1);
			String url = str[0]+"&"+str[1];
			fastDFSEntity.setUrl(url);
			System.out.println(fastDFSEntity.getUrl());
			String json = MAPPER.writeValueAsString(fastDFSEntity);
			return json;
		}
		fastDFSEntity.setUrl("文件太大，请上传少于1G文件");
		String json = MAPPER.writeValueAsString(fastDFSEntity);
		return json;
	 }
}
