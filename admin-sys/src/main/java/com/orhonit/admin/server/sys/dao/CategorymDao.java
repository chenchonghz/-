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

import com.orhonit.admin.server.sys.model.Categorym;

@Mapper
public interface CategorymDao {

    @Select("select * from categorym t where t.id = #{id}")
    Categorym getById(Long id);

    @Delete("delete from categorym where id = #{id}")
    int delete(Long id);

    @Update("update categorym t set parentId = #{parentId}, name = #{name}, createTime = #{createTime}, updateTime = #{updateTime} where t.id = #{id}")
    int update(Categorym categorym);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into categorym(parentId, name, createTime, updateTime) values(#{parentId}, #{name}, #{createTime}, #{updateTime})")
    int save(Categorym categorym);
    
    int count(@Param("params") Map<String, Object> params);

    List<Categorym> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
