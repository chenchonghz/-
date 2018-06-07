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

import com.orhonit.admin.server.sys.model.StudyVideo;

@Mapper
public interface StudyVideoDao {

    @Select("select * from studyVideo t where t.id = #{id}")
    StudyVideo getById(Long id);

    @Select("select a.*,b.name as name,c.name as categoryName from studyVideo as a left join expertinfo b on a.uid = b.uid left join category c on a.categoryId=c.id where a.id=#{id}")
    StudyVideo getId(Long id);
    
    @Delete("delete from studyVideo where id = #{id}")
    int delete(Long id);

    @Update("update studyVideo t set uid = #{uid}, categoryId = #{categoryId}, videoUrl = #{videoUrl}, title = #{title}, content = #{content}, coverPhoto = #{coverPhoto}, clicks = #{clicks}, status = #{status}, reason = #{reason} where t.id = #{id}")
    int update(StudyVideo studyVideo);
    
    @Update("update studyVideo t set status = #{status} where t.id = #{id}")
    int updatePass(StudyVideo studyVideo);
    
    @Update("update studyVideo t set status = #{status},reason=#{reason} where t.id = #{id}")
    int updateFail(StudyVideo studyVideo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into studyVideo(uid, categoryId, videoUrl, title, content, coverPhoto, clicks, status, reason) values(#{uid}, #{categoryId}, #{videoUrl}, #{title}, #{content}, #{coverPhoto}, #{clicks}, #{status}, #{reason})")
    int save(StudyVideo studyVideo);
    
    int count(@Param("params") Map<String, Object> params);

    List<StudyVideo> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
   
    @Select("select * from studyVideo where status=1 order by id desc limit #{start},10")
	List<StudyVideo> ten(Long start);
    @Select("select s.* from studyVideo s where s.status = 1 order by s.clicks desc limit 0,2")
    List<StudyVideo> frist();
    @Select("select * from studyVideo where status = 1")
	List<StudyVideo> getAll();
    @Update("update studyVideo set clicks = clicks + 1 where id = #{id}")
	void addStudyVideo(Integer id);
}
