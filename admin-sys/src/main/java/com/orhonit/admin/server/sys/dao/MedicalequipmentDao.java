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

import com.orhonit.admin.server.sys.model.Medicalequipment;

@Mapper
public interface MedicalequipmentDao {

    @Select("select * from medicalequipment t where t.id = #{id}")
    Medicalequipment getById(Long id);

    @Delete("delete from medicalequipment where id = #{id}")
    int delete(Long id);

    @Update("update medicalequipment t set name = #{name}, image = #{image}, content = #{content} where t.id = #{id}")
    int update(Medicalequipment medicalequipment);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into medicalequipment(name, image, content) values(#{name}, #{image}, #{content})")
    int save(Medicalequipment medicalequipment);
    
    int count(@Param("params") Map<String, Object> params);

    List<Medicalequipment> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from medicalequipment order by id desc")
	List<Medicalequipment> all();
}
