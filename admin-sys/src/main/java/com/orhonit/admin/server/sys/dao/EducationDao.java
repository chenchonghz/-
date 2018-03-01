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

import com.orhonit.admin.server.sys.model.Education;

@Mapper
public interface EducationDao {

    @Select("select * from education t where t.id = #{id}")
    Education getById(Long id);

    @Delete("delete from education where id = #{id}")
    int delete(Long id);

    @Update("update education t set name = #{name} where t.id = #{id}")
    int update(Education education);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into education(name) values(#{name})")
    int save(Education education);
    
    int count(@Param("params") Map<String, Object> params);

    List<Education> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
