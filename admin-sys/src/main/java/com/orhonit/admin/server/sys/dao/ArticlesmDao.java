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

import com.orhonit.admin.server.sys.model.Articlesm;

@Mapper
public interface ArticlesmDao {

    @Select("select * from articlesm t where t.id = #{id}")
    Articlesm getById(Long id);

    @Delete("delete from articlesm where id = #{id}")
    int delete(Long id);

    @Update("update articlesm t set title = #{title}, content = #{content}, status = #{status}, createTime = #{createTime}, updateTime = #{updateTime} where t.id = #{id}")
    int update(Articlesm articlesm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into articlesm(title, content, status, createTime, updateTime) values(#{title}, #{content}, #{status}, #{createTime}, #{updateTime})")
    int save(Articlesm articlesm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Articlesm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
