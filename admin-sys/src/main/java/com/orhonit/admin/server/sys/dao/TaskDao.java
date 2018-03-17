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

import com.orhonit.admin.server.sys.model.Task;

@Mapper
public interface TaskDao {

    @Select("select * from task t where t.id = #{id}")
    Task getById(Long id);

    @Delete("delete from task where id = #{id}")
    int delete(Long id);

    @Update("update task t set herdsmanId = #{herdsmanId}, expertId = #{expertId}, title = #{title}, content = #{content}, illnessCategoryId = #{illnessCategoryId}, processingMethod = #{processingMethod}, enclosure = #{enclosure}, status = #{status}, type = #{type}, good = #{good} where t.id = #{id}")
    int update(Task task);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into task(herdsmanId, expertId, title, content, illnessCategoryId, processingMethod, enclosure, status, type, good) values(#{herdsmanId}, #{expertId}, #{title}, #{content}, #{illnessCategoryId}, #{processingMethod}, #{enclosure}, #{status}, #{type}, #{good})")
    int save(Task task);
    
    int count(@Param("params") Map<String, Object> params);

    List<Task> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
