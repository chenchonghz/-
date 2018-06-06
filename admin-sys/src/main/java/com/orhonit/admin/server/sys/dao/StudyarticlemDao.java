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

import com.orhonit.admin.server.sys.model.Studyarticlem;

@Mapper
public interface StudyarticlemDao {

    @Select("select * from studyarticlem t where t.id = #{id}")
    Studyarticlem getById(Long id);

    @Delete("delete from studyarticlem where id = #{id}")
    int delete(Long id);

    @Update("update studyarticlem t set uid = #{uid}, title = #{title}, content = #{content}, coverPhoto = #{coverPhoto}, clicks = #{clicks}, status = #{status}, enclosure = #{enclosure}, reason = #{reason}, categoryId = #{categoryId} where t.id = #{id}")
    int update(Studyarticlem studyarticlem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into studyarticlem(uid, title, content, coverPhoto, clicks, status, enclosure, reason, categoryId) values(#{uid}, #{title}, #{content}, #{coverPhoto}, #{clicks}, #{status}, #{enclosure}, #{reason}, #{categoryId})")
    int save(Studyarticlem studyarticlem);
    
    int count(@Param("params") Map<String, Object> params);

    List<Studyarticlem> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from studyarticlem where status = 1 order by id desc limit #{start},10")
	List<Studyarticlem> ten(long start);
    @Select("select * from studyarticlem where status = 1")
	List<Studyarticlem> getAll();
    @Select("select * from studyarticlem where uid = #{uid} and status = 1")
	List<Studyarticlem> getByUid(Long uid);
    @Update("update studyarticlem set clicks = clicks + 1 where id = #{id}")
	void addStudyArticlem(Integer id);
    @Select("select a.*,b.name as name,c.name as categoryName from studyarticlem as a left join expertinfo b on a.uid = b.uid left join category c on a.categoryId=c.id where a.id=#{id}")
	Studyarticlem getId(Long id);
    @Update("update studyarticlem t set status = #{status} where t.id = #{id}")
	int updatePass(Studyarticlem studyarticlem);
    @Update("update studyarticlem t set status = #{status},reason=#{reason} where t.id = #{id}")
	int updateFail(Studyarticlem studyarticlem);

}
