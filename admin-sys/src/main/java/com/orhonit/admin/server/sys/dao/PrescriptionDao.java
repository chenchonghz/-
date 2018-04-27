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

import com.orhonit.admin.server.sys.dto.PrescriptionDto;
import com.orhonit.admin.server.sys.dto.drugstoreDto;
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
    @Select("SELECT p.*,d.pharmacyName,s.username FROM prescription p LEFT JOIN drugstoreinfo d ON p.drugstoreId = d.uid LEFT JOIN sys_user s ON s.id = d.uid WHERE p.taskId = #{taskId}")
	List<PrescriptionDto> getP(Long taskId);
    @Select("SELECT T.*, N.`name`,S.username FROM (SELECT p.taskId,P.drugstoreId FROM `prescription` AS p WHERE p.drugstoreId = #{userId} GROUP BY p.taskId,P.drugstoreId) AS T LEFT JOIN task AS K ON K.id=T.taskId LEFT JOIN newherdsman AS N ON N.uid = K.herdsmanId LEFT JOIN sys_user AS S ON S.id = N.uid;")
	List<drugstoreDto> drugsGetList(Long userId);
	@Select("select * from prescription p where p.taskId=#{arg0} and p.drugstoreId = #{arg1}")
	List<Prescription> selectPre(int taskId, int drugstoreId);
	@Select("update prescription set status = 1 where taskId = #{arg0} and drugstoreId = #{arg1}")
	void updateStatus(Long taskId, Long userId);
}
