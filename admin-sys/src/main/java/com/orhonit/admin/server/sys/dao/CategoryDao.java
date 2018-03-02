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

import com.orhonit.admin.server.sys.model.Category;

@Mapper
public interface CategoryDao {

    @Select("select * from category t where t.id = #{id}")
    Category getById(Long id);
    
    @Select("select * from category t where t.id = #{id}")
    Category getByParentId(int id);
    
    @Select("select * from category t order by t.id")
    List<Category> listAll();
    
    @Delete("delete from category where id = #{id}")
    int delete(Long id);

    @Update("update category t set parentId = #{parentId}, name = #{name}, updateTime = #{updateTime} where t.id = #{id}")
    int update(Category category);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into category(parentId, name, createTime, updateTime) values(#{parentId}, #{name}, now(), now())")
    int save(Category category);
    
    int count(@Param("params") Map<String, Object> params);

    List<Category> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
