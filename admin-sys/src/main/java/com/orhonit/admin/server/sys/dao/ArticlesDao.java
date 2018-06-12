package com.orhonit.admin.server.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.orhonit.admin.server.sys.model.Articles;

@Mapper
public interface ArticlesDao {

	@Select("select * from articles t where t.id = #{id}")
	Articles getById(Long id);

	@Delete("delete from articles where id = #{id}")
	int delete(Long id);

	@Update("update articles t set title = #{title},titleMeng = #{titleMeng}, contentMeng = #{contentMeng}, content = #{content}, status = #{status}, updateTime = #{updateTime} where t.id = #{id}")
	int update(Articles articles);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into articles(title, titleMeng, content, contentMeng, status, createTime, updateTime) values(#{title}, #{titleMeng}, #{content}, #{contentMeng}, #{status}, #{createTime}, #{updateTime})")
	int save(Articles articles);

	int count(@Param("params") Map<String, Object> params);

	List<Articles> list(@Param("params") Map<String, Object> params, @Param("start") Integer start,
			@Param("length") Integer length);
}
