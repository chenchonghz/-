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

import com.orhonit.admin.server.sys.model.Comment;

@Mapper
public interface CommentDao {

    @Select("select * from comment t where t.id = #{id}")
    Comment getById(Long id);

    @Delete("delete from comment where id = #{id}")
    int delete(Long id);

    @Update("update comment t set herdsmanId = #{herdsmanId}, content = #{content}, status = #{status}, cateId = #{cateId}, chlidrenId = #{chlidrenId} where t.id = #{id}")
    int update(Comment comment);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into comment(herdsmanId, content, status, cateId, chlidrenId) values(#{herdsmanId}, #{content}, #{status}, #{cateId}, #{chlidrenId})")
    int save(Comment comment);
    
    int count(@Param("params") Map<String, Object> params);

    List<Comment> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
