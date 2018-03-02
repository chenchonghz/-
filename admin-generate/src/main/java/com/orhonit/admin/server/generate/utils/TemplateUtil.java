package com.orhonit.admin.server.generate.utils;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.orhonit.admin.server.common.utils.FileUtil;
import com.orhonit.admin.server.common.utils.StrUtil;
import com.orhonit.admin.server.generate.model.GenerateInput;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "adminLogger")
public class TemplateUtil {

	public static String getTemplete(String fileName) {
		String path = TemplateUtil.class.getResource("").getPath() + fileName;
		return FileUtil.getText(path);
	}

	public static void saveJava(GenerateInput input) {
		String path = input.getPath();
		String beanPackageName = input.getBeanPackageName();
		String beanName = input.getBeanName();
		List<String> beanFieldName = input.getBeanFieldName();
		List<String> beanFieldType = input.getBeanFieldType();
		List<String> beanFieldValue = input.getBeanFieldValue();

		String text = getTemplete("java.txt");
		text = text.replace("{beanPackageName}", beanPackageName).replace("{beanName}", beanName);

		String imports = "";
		if (beanFieldType.contains(BigDecimal.class.getSimpleName())) {
			imports += "import " + BigDecimal.class.getName() + ";\n";
		}
		if (beanFieldType.contains(Date.class.getSimpleName())) {
			imports += "import " + Date.class.getName() + ";";
		}

		text = text.replace("{import}", imports);
		String filelds = getFields(beanFieldName, beanFieldType, beanFieldValue);
		text = text.replace("{filelds}", filelds);

		FileUtil.saveTextFile(text, path + File.separator + getPackagePath(beanPackageName) + beanName + ".java");
		log.debug("生成java model：{}模板", beanName);
	}

	private static String getFields(List<String> beanFieldName, List<String> beanFieldType,
			List<String> beanFieldValue) {
		StringBuffer buffer = new StringBuffer();
		int size = beanFieldName.size();
		for (int i = 0; i < size; i++) {
			String name = beanFieldName.get(i);
			if ("id".equals(name) || "createTime".equals(name) || "updateTime".equals(name)) {
				continue;
			}
			String type = beanFieldType.get(i);
			buffer.append("\tprivate ").append(type).append(" ").append(name);
			// 默认值
//			String value = beanFieldValue.get(i);
//			if (!StringUtils.isEmpty(value)) {
//				buffer.append(" = ");
//				if (type.equals(String.class.getSimpleName())) {
//					value = "\"" + value + "\"";
//				} else if (type.equals(Double.class.getSimpleName())) {
//					value = value + "D";
//				} else if (type.equals(Float.class.getSimpleName())) {
//					value = value + "F";
//				} else if (type.equals(BigDecimal.class.getSimpleName())) {
//					value = "new BigDecimal(" + value + ")";
//				}
//
//				buffer.append(value);
//			}
			buffer.append(";\n");
		}

		return buffer.toString();
	}

	public static void saveJavaDao(GenerateInput input) {
		String path = input.getPath();
		String tableName = input.getTableName();
		String beanPackageName = input.getBeanPackageName();
		String beanName = input.getBeanName();
		String daoPackageName = input.getDaoPackageName();
		String daoName = input.getDaoName();

		String text = getTemplete("dao.txt");
		text = text.replace("{daoPackageName}", daoPackageName);
		text = text.replace("{beanPackageName}", beanPackageName);
		text = text.replace("{daoName}", daoName);
		text = text.replace("{table_name}", tableName);
		text = text.replace("{beanName}", beanName);
		text = text.replace("{beanParamName}", lowerFirstChar(beanName));
		String sets = getUpdateSets(input.getColumnNames(), input.getBeanFieldName());
		text = text.replace("{update_sets}", sets);

		String insertColumns = getInsertColumns(input.getColumnNames());
		text = text.replace("{insert_columns}", insertColumns);
		String insertValues = getInsertValues(input.getColumnNames(), input.getBeanFieldName());
		text = text.replace("{insert_values}", insertValues);
		FileUtil.saveTextFile(text, path + File.separator + getPackagePath(daoPackageName) + daoName + ".java");
		log.debug("生成java dao：{}模板", beanName);

		text = getTemplete("mapper.xml");
		text = text.replace("{daoPackageName}", daoPackageName);
		text = text.replace("{daoName}", daoName);
		text = text.replace("{table_name}", tableName);
		text = text.replace("{beanName}", beanName);
		FileUtil.saveTextFile(text, path + File.separator + getPackagePath(daoPackageName) + beanName + "Mapper.xml");
	}

	private static String getInsertValues(List<String> columnNames, List<String> beanFieldName) {
		StringBuffer buffer = new StringBuffer();
		int size = columnNames.size();
		for (int i = 0; i < size; i++) {
			String column = columnNames.get(i);
			if (!"id".equals(column)) {
				buffer.append("#{").append(beanFieldName.get(i)).append("}, ");
			}
		}

		String sets = StringUtils.substringBeforeLast(buffer.toString(), ",");
		return sets;
	}

	private static String getInsertColumns(List<String> columnNames) {
		StringBuffer buffer = new StringBuffer();
		int size = columnNames.size();
		for (int i = 0; i < size; i++) {
			String column = columnNames.get(i);
			if (!"id".equals(column)) {
				buffer.append(column).append(", ");
			}
		}

		String insertColumns = StringUtils.substringBeforeLast(buffer.toString(), ",");
		return insertColumns;
	}

	private static String getUpdateSets(List<String> columnNames, List<String> beanFieldName) {
		StringBuffer buffer = new StringBuffer();
		int size = columnNames.size();
		for (int i = 0; i < size; i++) {
			String column = columnNames.get(i);
			if (!"id".equals(column)) {
				buffer.append(column).append(" = ").append("#{").append(beanFieldName.get(i)).append("}, ");
			}
		}

		String sets = StringUtils.substringBeforeLast(buffer.toString(), ",");
		return sets;
	}

	/**
	 * 变量名
	 * 
	 * @param beanName
	 * @return
	 */
	public static String lowerFirstChar(String beanName) {
		String name = StrUtil.str2hump(beanName);
		String firstChar = name.substring(0, 1);
		name = name.replaceFirst(firstChar, firstChar.toLowerCase());

		return name;
	}

	private static String getPackagePath(String packageName) {
		String packagePath = packageName.replace(".", "/");
		if (!packagePath.endsWith("/")) {
			packagePath = packagePath + "/";
		}

		return packagePath;
	}

	public static void saveController(GenerateInput input) {
		String path = input.getPath();
		String beanPackageName = input.getBeanPackageName();
		String beanName = input.getBeanName();
		String daoPackageName = input.getDaoPackageName();
		String daoName = input.getDaoName();

		String text = getTemplete("controller.txt");
		text = text.replace("{daoPackageName}", daoPackageName);
		text = text.replace("{beanPackageName}", beanPackageName);
		text = text.replace("{daoName}", daoName);
		text = text.replace("{daoParamName}", lowerFirstChar(daoName));
		text = text.replace("{beanName}", beanName);
		text = text.replace("{beanParamName}", lowerFirstChar(beanName));
		text = text.replace("{controllerPkgName}", input.getControllerPkgName());
		text = text.replace("{controllerName}", input.getControllerName());

		FileUtil.saveTextFile(text, path + File.separator + getPackagePath(input.getControllerPkgName())
				+ input.getControllerName() + ".java");
		log.debug("生成controller：{}模板", beanName);
	}

	public static void saveHtmlList(GenerateInput input) {
		String path = input.getPath();
		String beanName = input.getBeanName();
		String beanParamName = lowerFirstChar(beanName);

		String text = getTemplete("htmlList.txt");
		text = text.replace("{beanParamName}", beanParamName);
		List<String> beanFieldNames = input.getBeanFieldName();
		text = text.replace("{columnsDatas}", getHtmlColumnsDatas(beanFieldNames));
		text = text.replace("{columnDefs}", getHtmlColumnDefs(beanFieldNames));
		text = text.replace("{ths}", getHtmlThs(beanFieldNames));

		FileUtil.saveTextFile(text, path + File.separator + beanParamName + "List.html");
		log.debug("生成查询页面：{}模板", beanName);
	}

	private static String getHtmlThs(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		beanFieldNames.forEach(b -> {
			builder.append("\t\t\t\t\t\t\t\t\t<th>{beanFieldName}</th>\n".replace("{beanFieldName}", b));
		});
		return builder.toString();
	}

	private static String getHtmlColumnsDatas(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		beanFieldNames.forEach(b -> {
			builder.append("\t\t\t\t{\"data\" : \"{beanFieldName}\", \"defaultContent\" : \"\"},\n"
					.replace("{beanFieldName}", b));
		});
		return builder.toString();
	}

	private static String getHtmlColumnDefs(List<String> beanFieldNames) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < beanFieldNames.size(); i++) {
			builder.append("\t\t\t\t{\"name\" : \"" + beanFieldNames.get(i) + "\", \"targets\" : \"" + i + "\"},\n");
		}
		;
		return builder.toString();
	}
}
