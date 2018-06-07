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
    
    @Select("select * from drugstoreinfo t where t.uid = #{id}")
    Drugstoreinfo getUId(Long id);

    @Delete("delete from drugstoreinfo where id = #{id}")
    int delete(Long id);
    
    @Select("select * from drugstoreinfo t where t.status = #{id}")
    List<Drugstoreinfo> getByStatus(int id);
    
    @Delete("delete from drugstoreinfo where uid = #{id}")
    int deleteUid(Long id);
    
    @Select("select * from drugstoreinfo t where t.uid = #{id}")
    Drugstoreinfo ById(Long id);
    
    @Select("select a.*,b.name as provinceName,c.name as cityName,d.name as areaName,e.name as provinceNameMeng,f.name as cityNameMeng,g.name as areaNameMeng from drugstoreinfo as a left join district b on a.province = b.id left join district c on a.city=c.id left join district d on a.area=d.id left join districtm e on a.provinceMeng = e.id left join districtm f on a.cityMeng = f.id left join districtm g on a.areaMeng = g.id where a.uid=#{uid}") 
    Drugstoreinfo getByUid(int uid);
    
    @Update("update drugstoreinfo t set uid = #{uid}, pharmacyName = #{pharmacyName}, pharmacyNameMeng = #{pharmacyNameMeng}, drugstoreLicense = #{drugstoreLicense}, drugstoreInformation = #{drugstoreInformation}, drugstoreInformationMeng = #{drugstoreInformationMeng}, province = #{province}, provinceMeng = #{provinceMeng}, city = #{city}, cityMeng = #{cityMeng}, area = #{area}, areaMeng = #{areaMeng}, address = #{address}, addressMeng = #{addressMeng}, headerUrl = #{headerUrl}, createTime = #{createTime}, updateTime = #{updateTime}, status = #{status}, reason = #{reason} where t.id = #{id}")
    int update(Drugstoreinfo drugstoreinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into drugstoreinfo(uid, pharmacyName, pharmacyNameMeng, drugstoreLicense, drugstoreInformation, drugstoreInformationMeng, province, provinceMeng, city, cityMeng, area, areaMeng, address, addressMeng, headerUrl, createTime, updateTime, status, reason) values(#{uid}, #{pharmacyName}, #{pharmacyNameMeng}, #{drugstoreLicense}, #{drugstoreInformation}, #{drugstoreInformationMeng}, #{province}, #{provinceMeng}, #{city}, #{cityMeng}, #{area}, #{areaMeng}, #{address}, #{addressMeng}, #{headerUrl}, #{createTime}, #{updateTime}, #{status}, #{reason})")
    int save(Drugstoreinfo drugstoreinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<Drugstoreinfo> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    
    @Select("select a.*,b.name as provinceName,c.name as cityName,d.name as areaName,e.name as provinceNameMeng,f.name as cityNameMeng,g.name as areaNameMeng from drugstoreinfo as a left join district b on a.province = b.id left join district c on a.city=c.id left join district d on a.area=d.id left join districtm e on a.provinceMeng = e.id left join districtm f on a.cityMeng = f.id left join districtm g on a.areaMeng = g.id where a.status=1 order by id desc limit #{start},10")
	List<Drugstoreinfo> ten(Long start);
    @Update("update drugstoreinfo t set  pharmacyName = #{pharmacyName}, pharmacyNameMeng = #{pharmacyNameMeng}, drugstoreLicense = #{drugstoreLicense}, drugstoreInformation = #{drugstoreInformation}, drugstoreInformationMeng = #{drugstoreInformationMeng}, province = #{province}, provinceMeng = #{provinceMeng}, city = #{city}, cityMeng = #{cityMeng}, area = #{area}, areaMeng = #{areaMeng}, address = #{address}, addressMeng = #{addressMeng}, headerUrl = #{headerUrl}, updateTime = #{updateTime}, status = #{status}, reason = #{reason} where t.id = #{id}")
	void AppUpdate(Drugstoreinfo drugstoreinfo);
    @Select("select * from drugstoreinfo where area = #{area} limit 0,10")
	List<Drugstoreinfo> selectByNewHerdsManArea(Integer area);
    @Select("select * from drugstoreinfo where city = #{arg0} and area != #{arg1}")
	List<Drugstoreinfo> selectByNewHerdsManCity(Integer city, Integer area);
    @Select("select * from drugstoreinfo where areaMeng = #{areaMeng} limit 0,10")
	List<Drugstoreinfo> selectByNewHerdsManAreaMeng(Integer areaMeng);
    @Select("select * from drugstoreinfo where cityMeng = #{arg0} and areaMeng != #{arg1}")
	List<Drugstoreinfo> selectByNewHerdsManCityMeng(Integer cityMeng, Integer areaMeng);
}
