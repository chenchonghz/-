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

@Mapper
public interface LiveshowDao {

    @Select("select * from liveshow t where t.id = #{id}")
    Liveshow getById(Long id);

    @Delete("delete from liveshow where id = #{id}")
    int delete(Long id);
    
    @Select("select t.*,e.name as name from liveshow t left join expertinfo e on e.uid = t.onlineApplyId where t.id = #{id}")
    Liveshow getId(Long id);

    @Update("update liveshow t set coverPhoto = #{coverPhoto}, onlineApplyId = #{onlineApplyId}, onlineQuantity = #{onlineQuantity}, videoName = #{videoName}, liveHome = #{liveHome}, startTime = #{startTime}, endTime = #{endTime}, status = #{status} where t.id = #{id}")
    int update(Liveshow liveshow);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into liveshow(coverPhoto, onlineApplyId, onlineQuantity, videoName, liveHome, startTime, endTime) values(#{coverPhoto}, #{onlineApplyId}, #{onlineQuantity}, #{videoName}, #{liveHome}, #{startTime}, #{endTime},#{status})")
    int save(Liveshow liveshow);
    
    int count(@Param("params") Map<String, Object> params);

    List<Liveshow> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from liveshow where startTime < #{time} and endTime > #{time}")
	List<Liveshow> getLiveNow(String time);
    @Update("update liveshow set onlineQuantity = onlineQuantity + 1 where id = #{id}")
	void addPeople(Integer id);
    @Update("update liveshow set onlineQuantity = onlineQuantity - 1 where id = #{id}")
	void lessPeople(Integer id);
    @Update("update liveshow t set t.status = #{status} where t.id = #{id}")
	int updateStatus(Liveshow liveshow);
}
