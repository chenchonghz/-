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

import com.orhonit.admin.server.sys.model.Prescription;

@Mapper
public interface PrescriptionDao {

    @Select("select * from prescription t where t.id = #{id}")
    Prescription getById(Long id);

    @Delete("delete from prescription where id = #{id}")
    int delete(Long id);

    @Update("update prescription t set taskId = #{taskId}, drugstoreId = #{drugstoreId}, drugId = #{drugId}, drugName = #{drugName}, drugNumber = #{drugNumber}, status = #{status} where t.id = #{id}")
    int update(Prescription prescription);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into prescription(taskId, drugstoreId, drugId, drugName, drugNumber, status) values(#{taskId}, #{drugstoreId}, #{drugId}, #{drugName}, #{drugNumber}, #{status})")
    int save(Prescription prescription);
    
    int count(@Param("params") Map<String, Object> params);

    List<Prescription> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}