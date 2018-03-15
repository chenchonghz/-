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

import com.orhonit.admin.server.sys.model.Illnesscategory;

@Mapper
public interface IllnesscategoryDao {

    @Select("select * from illnesscategory t where t.id = #{id}")
    Illnesscategory getById(Long id);

    @Delete("delete from illnesscategory where id = #{id}")
    int delete(Long id);

    @Update("update illnesscategory t set parentId = #{parentId}, name = #{name}, createTime = #{createTime}, updateTime = #{updateTime} where t.id = #{id}")
    int update(Illnesscategory illnesscategory);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into illnesscategory(parentId, name, createTime, updateTime) values(#{parentId}, #{name}, #{createTime}, #{updateTime})")
    int save(Illnesscategory illnesscategory);
    
    int count(@Param("params") Map<String, Object> params);

    List<Illnesscategory> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
