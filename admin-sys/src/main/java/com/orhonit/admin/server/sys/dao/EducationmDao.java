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

import com.orhonit.admin.server.sys.model.Educationm;

@Mapper
public interface EducationmDao {

    @Select("select * from educationm t where t.id = #{id}")
    Educationm getById(Long id);

    @Delete("delete from educationm where id = #{id}")
    int delete(Long id);

    @Update("update educationm t set name = #{name} where t.id = #{id}")
    int update(Educationm educationm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into educationm(name) values(#{name})")
    int save(Educationm educationm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Educationm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
