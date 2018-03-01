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

import com.orhonit.admin.server.sys.model.Districtm;

@Mapper
public interface DistrictmDao {

    @Select("select * from districtm t where t.id = #{id}")
    Districtm getById(Long id);

    @Delete("delete from districtm where id = #{id}")
    int delete(Long id);
    
    @Select("select * from districtm t where t.id = #{id}")
    Districtm getId(int id);
    
    @Select("select * from districtm t where t.upid =#{id}")
    List<Districtm> getByUpid(int id);

    @Update("update districtm t set upid = #{upid}, name = #{name}, type = #{type}, displayorder = #{displayorder}, spelling = #{spelling} where t.id = #{id}")
    int update(Districtm districtm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into districtm(upid, name, type, displayorder, spelling) values(#{upid}, #{name}, #{type}, #{displayorder}, #{spelling})")
    int save(Districtm districtm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Districtm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
