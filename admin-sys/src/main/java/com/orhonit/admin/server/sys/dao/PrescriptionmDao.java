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

import com.orhonit.admin.server.sys.dto.PrescriptionmDto;
import com.orhonit.admin.server.sys.dto.drugstoremDto;
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
    @Select("SELECT p.*, d.pharmacyNameMeng, s.username FROM prescriptionm p LEFT JOIN drugstoreinfo d ON p.drugstoreId = d.uid LEFT JOIN sys_user s ON s.id = d.uid WHERE p.taskId = #{taskId}")
	List<PrescriptionmDto> getP(Long taskId);
    @Select("SELECT T.*, N.`nameMeng`,S.username FROM (SELECT p.taskId,P.drugstoreId FROM `prescriptionm` AS p WHERE p.drugstoreId = #{userId} GROUP BY p.taskId,P.drugstoreId) AS T LEFT JOIN taskm AS K ON K.id=T.taskId LEFT JOIN newherdsman AS N ON N.uid = K.herdsmanId LEFT JOIN sys_user AS S ON S.id = N.uid;")
	List<drugstoremDto> drugsGetList(Long userId);
	@Select("select * from prescriptionm p where p.taskId=#{arg0} and p.drugstoreId = #{arg1}")
	List<Prescriptionm> selectPro(int taskId, int drugstoreId);
}
