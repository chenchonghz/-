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

import com.orhonit.admin.server.sys.model.Herdsmaninfo;

@Mapper
public interface HerdsmaninfoDao {

    @Select("select * from herdsmaninfo t where t.id = #{id}")
    Herdsmaninfo getById(Long id);
 
    @Select("select a.*,b.name as provinceName,c.name as cityName,d.name as areaName from herdsmaninfo as a left join district b on a.province = b.id left join district c on a.city=c.id left join district d on a.area=d.id where uid=#{uid}") 
    Herdsmaninfo getByUid(int uid);
    
    @Delete("delete from herdsmaninfo where id = #{id}")
    int delete(Long id);
    
    @Select("select * from herdsmaninfo t where t.uid=#{id}")
    Herdsmaninfo getUid(Long id);
    
    @Delete("delete from herdsmaninfo where uid = #{id}")
    int deleteUid(Long id);

    @Update("update herdsmaninfo t set uid = #{uid}, province = #{province}, city = #{city}, area = #{area}, address = #{address}, headerUrl = #{headerUrl}, sex = #{sex}, createTime = #{createTime}, updateTime = #{updateTime}, cardNumber = #{cardNumber}, name = #{name} where t.id = #{id}")
    int update(Herdsmaninfo herdsmaninfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into herdsmaninfo(uid, province, city, area, address, headerUrl, sex, createTime, updateTime, cardNumber, name) values(#{uid}, #{province}, #{city}, #{area}, #{address}, #{headerUrl}, #{sex}, #{createTime}, #{updateTime}, #{cardNumber}, #{name})")
    int save(Herdsmaninfo herdsmaninfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<Herdsmaninfo> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
