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

import com.orhonit.admin.server.sys.model.Studyarticlem;

@Mapper
public interface StudyarticlemDao {

    @Select("select * from studyarticlem t where t.id = #{id}")
    Studyarticlem getById(Long id);

    @Delete("delete from studyarticlem where id = #{id}")
    int delete(Long id);

    @Update("update studyarticlem t set uid = #{uid}, title = #{title}, content = #{content}, coverPhoto = #{coverPhoto}, clicks = #{clicks}, status = #{status}, enclosure = #{enclosure}, reason = #{reason}, categoryId = #{categoryId} where t.id = #{id}")
    int update(Studyarticlem studyarticlem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into studyarticlem(uid, title, content, coverPhoto, clicks, status, enclosure, reason, categoryId) values(#{uid}, #{title}, #{content}, #{coverPhoto}, #{clicks}, #{status}, #{enclosure}, #{reason}, #{categoryId})")
    int save(Studyarticlem studyarticlem);
    
    int count(@Param("params") Map<String, Object> params);

    List<Studyarticlem> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from studyarticlem where status = 1 order by id desc limit #{start},10")
	List<Studyarticlem> ten(long start);
}
