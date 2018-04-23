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

import com.orhonit.admin.server.sys.model.Slide;

@Mapper
public interface SlideDao {

    @Select("select * from slide t where t.id = #{id}")
    Slide getById(Long id);

    @Delete("delete from slide where id = #{id}")
    int delete(Long id);

    @Update("update slide t set title = #{title}, content = #{content}, pic = #{pic}, createTime = #{createTime}, status = #{status} where t.id = #{id}")
    int update(Slide slide);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into slide(title, content, pic, createTime, status) values(#{title}, #{content}, #{pic}, #{createTime}, #{status})")
    int save(Slide slide);
    
    int count(@Param("params") Map<String, Object> params);

    List<Slide> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    
    @Select("select * from slide where status = 1")
	List<Slide> getSlideList();
}
