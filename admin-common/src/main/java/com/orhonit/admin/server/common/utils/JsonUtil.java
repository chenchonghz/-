package com.orhonit.admin.server.common.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json对象转换工具类
 * 
 * @author caodw
 *
 */
public class JsonUtil {
	private static ObjectMapper mapper = null;

	static {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public static <T> T fromJson(String jsonString, Class<T> type) {
		if (StringUtils.isBlank(jsonString)) {
			return null;
		} else {
			try {
				return mapper.readValue(jsonString, type);
			} catch (IOException arg2) {
				arg2.printStackTrace();
				return null;
			}
		}
	}

	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException arg1) {
			arg1.printStackTrace();
			return null;
		}
	}

}