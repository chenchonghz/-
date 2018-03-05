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

import com.orhonit.admin.server.sys.model.Medicalequipmentm;

@Mapper
public interface MedicalequipmentmDao {

    @Select("select * from medicalequipmentm t where t.id = #{id}")
    Medicalequipmentm getById(Long id);

    @Delete("delete from medicalequipmentm where id = #{id}")
    int delete(Long id);

    @Update("update medicalequipmentm t set name = #{name}, image = #{image}, content = #{content} where t.id = #{id}")
    int update(Medicalequipmentm medicalequipmentm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into medicalequipmentm(name, image, content) values(#{name}, #{image}, #{content})")
    int save(Medicalequipmentm medicalequipmentm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Medicalequipmentm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
