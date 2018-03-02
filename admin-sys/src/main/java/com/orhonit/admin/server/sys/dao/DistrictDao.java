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

import com.orhonit.admin.server.sys.model.District;

@Mapper
public interface DistrictDao {

    @Select("select * from district t where t.id = #{id}")
    District getById(Long id);
    
    @Select("select * from district t where t.id = #{id}")
    District getId(int id);
    
    @Select("select * from district t where t.upid =#{id}")
    List<District> getByUpid(int id);

    @Delete("delete from district where id = #{id}")
    int delete(Long id);

    @Update("update district t set upid = #{upid}, name = #{name}, type = #{type}, displayorder = #{displayorder}, spelling = #{spelling} where t.id = #{id}")
    int update(District district);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into district(upid, name, type, displayorder, spelling) values(#{upid}, #{name}, #{type}, #{displayorder}, #{spelling})")
    int save(District district);
    
    int count(@Param("params") Map<String, Object> params);

    List<District> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
