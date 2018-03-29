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

import com.orhonit.admin.server.sys.model.Studyvideom;

@Mapper
public interface StudyvideomDao {

    @Select("select * from studyvideom t where t.id = #{id}")
    Studyvideom getById(Long id);

    @Delete("delete from studyvideom where id = #{id}")
    int delete(Long id);

    @Update("update studyvideom t set uid = #{uid}, categoryId = #{categoryId}, videoUrl = #{videoUrl}, title = #{title}, content = #{content}, coverPhoto = #{coverPhoto}, clicks = #{clicks}, status = #{status}, reason = #{reason} where t.id = #{id}")
    int update(Studyvideom studyvideom);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into studyvideom(uid, categoryId, videoUrl, title, content, coverPhoto, clicks, status, reason) values(#{uid}, #{categoryId}, #{videoUrl}, #{title}, #{content}, #{coverPhoto}, #{clicks}, #{status}, #{reason})")
    int save(Studyvideom studyvideom);
    
    int count(@Param("params") Map<String, Object> params);

    List<Studyvideom> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select s.* from studyVideom s,category c where c.parentId = 11 and s.categoryId = c.id  and s.status = 1 order by s.clicks desc limit 0,1")
	Studyvideom frist();
	@Select("select * from studyVideom where status=1 order by id desc limit #{start},10")
	List<Studyvideom> ten(long start);
}
