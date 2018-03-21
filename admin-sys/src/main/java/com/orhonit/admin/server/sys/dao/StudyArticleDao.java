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

import com.orhonit.admin.server.sys.model.StudyArticle;

@Mapper
public interface StudyArticleDao {

    @Select("select * from studyArticle t where t.id = #{id}")
    StudyArticle getById(Long id);

    @Delete("delete from studyArticle where id = #{id}")
    int delete(Long id);
    
    @Select("select a.*,b.name as name,c.name as categoryName from studyArticle as a left join expertinfo b on a.uid = b.uid left join category c on a.categoryId=c.id where a.id=#{id}")
    StudyArticle getId(Long id);
    
    @Update("update studyArticle t set uid = #{uid}, title = #{title},categroyId =#{categroyId} ,content = #{content}, coverPhoto = #{coverPhoto}, clicks = #{clicks}, status = #{status}, enclosure = #{enclosure} where t.id = #{id}")
    int update(StudyArticle studyArticle);
    
    @Update("update studyArticle t set status = #{status} where t.id = #{id}")
    int updatePass(StudyArticle studyArticle);
    
    @Update("update studyArticle t set status = #{status},reason=#{reason} where t.id = #{id}")
    int updateFail(StudyArticle studyArticle);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into studyArticle(uid, title, content, coverPhoto, clicks, status, enclosure, categoryId) values(#{uid}, #{title}, #{content}, #{coverPhoto}, #{clicks}, #{status}, #{enclosure},#{categoryId})")
    int save(StudyArticle studyArticle);
    
    int count(@Param("params") Map<String, Object> params);

    List<StudyArticle> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from studyArticle where status = 1 limit #{start},10")
	List<StudyArticle> ten(long start);
}
