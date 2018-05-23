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

import com.orhonit.admin.server.sys.model.Feedback;

@Mapper
public interface FeedbackDao {

    @Select("select * from feedback t where t.id = #{id}")
    Feedback getById(Long id);

    @Delete("delete from feedback where id = #{id}")
    int delete(Long id);

    @Update("update feedback t set uid = #{uid}, opinion = #{opinion} where t.id = #{id}")
    int update(Feedback feedback);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into feedback(uid, opinion) values(#{uid}, #{opinion})")
    int save(Feedback feedback);
    
    int count(@Param("params") Map<String, Object> params);

    List<Feedback> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
