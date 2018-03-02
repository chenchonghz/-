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

import com.orhonit.admin.server.sys.model.Drugstoreinfo;

@Mapper
public interface DrugstoreinfoDao {

    @Select("select * from drugstoreinfo t where t.id = #{id}")
    Drugstoreinfo getById(Long id);

    @Delete("delete from drugstoreinfo where id = #{id}")
    int delete(Long id);
    
    @Select("select * from drugstoreinfo t where t.status = #{id}")
    List<Drugstoreinfo> getByStatus(int id);
    
    @Delete("delete from drugstoreinfo where uid = #{id}")
    int deleteUid(Long id);
    
    @Select("select * from drugstoreinfo t where t.uid = #{id}")
    Drugstoreinfo ById(Long id);
    
    @Select("select a.*,b.name as provinceName,c.name as cityName,d.name as areaName from drugstoreinfo as a left join district b on a.province = b.id left join district c on a.city=c.id left join district d on a.area=d.id where uid=#{uid}") 
    Drugstoreinfo getByUid(int uid);
    
    @Update("update drugstoreinfo t set uid = #{uid}, pharmacyName = #{pharmacyName}, drugstoreLicense = #{drugstoreLicense}, drugstoreInformation = #{drugstoreInformation}, province = #{province}, city = #{city}, area = #{area}, address = #{address}, headerUrl = #{headerUrl},updateTime = now(), status = #{status} where t.id = #{id}")
    int update(Drugstoreinfo drugstoreinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into drugstoreinfo(uid, pharmacyName, drugstoreLicense, drugstoreInformation, province, city, area, address, headerUrl, createTime, updateTime, status) values(#{uid}, #{pharmacyName}, #{drugstoreLicense}, #{drugstoreInformation}, #{province}, #{city}, #{area}, #{address}, #{headerUrl},now(), now(), #{status})")
    int save(Drugstoreinfo drugstoreinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<Drugstoreinfo> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
