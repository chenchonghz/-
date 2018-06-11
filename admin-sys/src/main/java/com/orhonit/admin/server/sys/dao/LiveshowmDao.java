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

import com.orhonit.admin.server.sys.model.Liveshow;
import com.orhonit.admin.server.sys.model.Liveshowm;

@Mapper
public interface LiveshowmDao {

    @Select("select * from liveshowm t where t.id = #{id}")
    Liveshowm getById(Long id);
    
    @Select("select t.*,e.nameMeng as name from liveshowm t left join expertinfo e on e.uid = t.onlineApplyId where t.id = #{id}")
    Liveshowm getId(Long id);

    @Delete("delete from liveshowm where id = #{id}")
    int delete(Long id);

    @Update("update liveshowm t set coverPhoto = #{coverPhoto}, onlineApplyId = #{onlineApplyId}, onlineQuantity = #{onlineQuantity}, videoName = #{videoName}, liveHome = #{liveHome}, startTime = #{startTime}, endTime = #{endTime} where t.id = #{id}")
    int update(Liveshowm liveshowm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into liveshowm(coverPhoto, onlineApplyId, onlineQuantity, videoName, liveHome, startTime, endTime,status) values(#{coverPhoto}, #{onlineApplyId}, #{onlineQuantity}, #{videoName}, #{liveHome}, #{startTime}, #{endTime},#{status})")
    int save(Liveshowm liveshowm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Liveshowm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from liveshowm where startTime < #{time} and endTime > #{time}")
	List<Liveshow> getLiveNow(String time);
    @Update("update liveshowm set onlineQuantity = onlineQuantity + 1 where id = #{id}")
	void addPeople(Integer id);
    @Update("update liveshowm set onlineQuantity = onlineQuantity - 1 where id = #{id}")
	void lessPeople(Integer id);
    @Update("update liveshowm t set t.status = #{status} where t.id = #{id}")
	int updateStatus(Liveshowm liveshowm);
}
