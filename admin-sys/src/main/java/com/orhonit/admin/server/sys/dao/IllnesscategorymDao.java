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

import com.orhonit.admin.server.sys.model.Illnesscategorym;

@Mapper
public interface IllnesscategorymDao {

    @Select("select * from illnesscategorym t where t.id = #{id}")
    Illnesscategorym getById(Long id);

    @Delete("delete from illnesscategorym where id = #{id}")
    int delete(Long id);

    @Update("update illnesscategorym t set parentId = #{parentId}, name = #{name}, createTime = #{createTime}, updateTime = #{updateTime} where t.id = #{id}")
    int update(Illnesscategorym illnesscategorym);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into illnesscategorym(parentId, name, createTime, updateTime) values(#{parentId}, #{name}, #{createTime}, #{updateTime})")
    int save(Illnesscategorym illnesscategorym);
    
    int count(@Param("params") Map<String, Object> params);

    List<Illnesscategorym> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from illnesscategorym")
	List<Illnesscategorym> AppList();
}
