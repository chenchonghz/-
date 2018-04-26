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

import com.orhonit.admin.server.sys.model.Prescriptionm;

@Mapper
public interface PrescriptionmDao {

    @Select("select * from prescriptionm t where t.id = #{id}")
    Prescriptionm getById(Long id);

    @Delete("delete from prescriptionm where id = #{id}")
    int delete(Long id);

    @Update("update prescriptionm t set taskId = #{taskId}, drugstoreId = #{drugstoreId}, drugId = #{drugId}, drugName = #{drugName}, drugNumber = #{drugNumber}, status = #{status} where t.id = #{id}")
    int update(Prescriptionm prescriptionm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into prescriptionm(taskId, drugstoreId, drugId, drugName, drugNumber, status) values(#{taskId}, #{drugstoreId}, #{drugId}, #{drugName}, #{drugNumber}, #{status})")
    int save(Prescriptionm prescriptionm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Prescriptionm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}