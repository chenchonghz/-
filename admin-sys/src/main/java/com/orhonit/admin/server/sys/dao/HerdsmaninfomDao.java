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

import com.orhonit.admin.server.sys.model.Herdsmaninfom;

@Mapper
public interface HerdsmaninfomDao {

    @Select("select * from herdsmaninfom t where t.id = #{id}")
    Herdsmaninfom getById(Long id);

    @Delete("delete from herdsmaninfom where id = #{id}")
    int delete(Long id);

    @Update("update herdsmaninfom t set uid = #{uid}, province = #{province}, city = #{city}, area = #{area}, address = #{address}, headerUrl = #{headerUrl}, sex = #{sex}, createTime = #{createTime}, updateTime = #{updateTime}, cardNumber = #{cardNumber}, name = #{name} where t.id = #{id}")
    int update(Herdsmaninfom herdsmaninfom);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into herdsmaninfom(uid, province, city, area, address, headerUrl, sex, createTime, updateTime, cardNumber, name) values(#{uid}, #{province}, #{city}, #{area}, #{address}, #{headerUrl}, #{sex}, #{createTime}, #{updateTime}, #{cardNumber}, #{name})")
    int save(Herdsmaninfom herdsmaninfom);
    
    int count(@Param("params") Map<String, Object> params);

    List<Herdsmaninfom> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
