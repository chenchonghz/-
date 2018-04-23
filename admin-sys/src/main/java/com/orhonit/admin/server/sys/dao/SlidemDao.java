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
import com.orhonit.admin.server.sys.model.Slidem;

@Mapper
public interface SlidemDao {

    @Select("select * from slidem t where t.id = #{id}")
    Slidem getById(Long id);

    @Delete("delete from slidem where id = #{id}")
    int delete(Long id);

    @Update("update slidem t set title = #{title}, content = #{content}, pic = #{pic}, createTime = #{createTime}, status = #{status} where t.id = #{id}")
    int update(Slidem slidem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into slidem(title, content, pic, createTime, status) values(#{title}, #{content}, #{pic}, #{createTime}, #{status})")
    int save(Slidem slidem);
    
    int count(@Param("params") Map<String, Object> params);

    List<Slidem> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);

    @Select("select * from slidem where status = 1")
	List<Slide> getSlidemList();
}
