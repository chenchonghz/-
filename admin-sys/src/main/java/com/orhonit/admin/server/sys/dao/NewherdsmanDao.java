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

import com.orhonit.admin.server.sys.model.Newherdsman;

@Mapper
public interface NewherdsmanDao {

    @Select("select * from newherdsman t where t.id = #{id}")
    Newherdsman getById(Long id);

    @Delete("delete from newherdsman where id = #{id}")
    int delete(Long id);

    @Update("update newherdsman t set uid = #{uid}, province = #{province}, city = #{city}, area = #{area}, address = #{address}, headerUrl = #{headerUrl}, sex = #{sex}, createTime = #{createTime}, updateTime = #{updateTime}, cardNumber = #{cardNumber}, name = #{name}, provinceMeng = #{provinceMeng}, cityMeng = #{cityMeng}, areaMeng = #{areaMeng}, addressMeng = #{addressMeng}, nameMeng = #{nameMeng} where t.id = #{id}")
    int update(Newherdsman newherdsman);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into newherdsman(uid, province, city, area, address, headerUrl, sex, createTime, updateTime, cardNumber, name, provinceMeng, cityMeng, areaMeng, addressMeng, nameMeng) values(#{uid}, #{province}, #{city}, #{area}, #{address}, #{headerUrl}, #{sex}, #{createTime}, #{updateTime}, #{cardNumber}, #{name}, #{provinceMeng}, #{cityMeng}, #{areaMeng}, #{addressMeng}, #{nameMeng})")
    int save(Newherdsman newherdsman);
    
    int count(@Param("params") Map<String, Object> params);

    List<Newherdsman> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    
    @Select("select a.*,b.name as provinceName,c.name as cityName,d.name as areaName from newherdsman as a left join district b on a.province = b.id left join district c on a.city=c.id left join district d on a.area=d.id where uid=#{id}") 
    Newherdsman getByUid(int id);
    
    @Select("select a.*,b.name as provinceNamem,c.name as cityNamem,d.name as areaNamem from newherdsman as a left join districtm b on a.provinceMeng = b.id left join districtm c on a.cityMeng=c.id left join districtm d on a.areaMeng=d.id where uid=#{id}") 
    Newherdsman getByUidm(int id);
    
    @Select("select * from newherdsman t where t.uid=#{id}")
    Newherdsman getUid(Long id);
    @Select("select a.*,b.name as provinceName,c.name as cityName,d.name as areaName ,x.name as provinceNamem,y.name as cityNamem,z.name as areaNamem from newherdsman as a left join district b on a.province = b.id left join district c on a.city=c.id left join district d on a.area=d.id     "
    		+ "left join districtm x on a.provinceMeng = x.id left join districtm y on a.cityMeng=y.id left join districtm z on a.areaMeng=z.id where uid=#{uid}")
	Newherdsman AppGetByUid(int uid);
    @Update("update newherdsman t set province = #{province}, city = #{city}, area = #{area}, address = #{address}, headerUrl = #{headerUrl}, sex = #{sex}, updateTime = #{updateTime}, cardNumber = #{cardNumber}, name = #{name}, provinceMeng = #{provinceMeng}, cityMeng = #{cityMeng}, areaMeng = #{areaMeng}, addressMeng = #{addressMeng}, nameMeng = #{nameMeng} where t.id = #{id}")
	void Appupdate(Newherdsman newherdsman);
}
