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

import com.orhonit.admin.server.sys.model.Taskm;

@Mapper
public interface TaskmDao {

    @Select("select * from taskm t where t.id = #{id}")
    Taskm getById(Long id);

    @Delete("delete from taskm where id = #{id}")
    int delete(Long id);

    @Update("update taskm t set herdsmanId = #{herdsmanId}, expertId = #{expertId}, title = #{title}, content = #{content}, illnessCategoryId = #{illnessCategoryId}, processingMethod = #{processingMethod}, enclosure = #{enclosure}, status = #{status}, type = #{type}, good = #{good}, herdsmanId = #{herdsmanId}, expertId = #{expertId}, title = #{title}, content = #{content}, illnessCategoryId = #{illnessCategoryId}, processingMethod = #{processingMethod}, enclosure = #{enclosure}, status = #{status}, type = #{type}, good = #{good} where t.id = #{id}")
    int update(Taskm taskm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into taskm(herdsmanId, expertId, title, content, illnessCategoryId, processingMethod, enclosure, status, type, good, herdsmanId, expertId, title, content, illnessCategoryId, processingMethod, enclosure, status, type, good) values(#{herdsmanId}, #{expertId}, #{title}, #{content}, #{illnessCategoryId}, #{processingMethod}, #{enclosure}, #{status}, #{type}, #{good}, #{herdsmanId}, #{expertId}, #{title}, #{content}, #{illnessCategoryId}, #{processingMethod}, #{enclosure}, #{status}, #{type}, #{good})")
    int save(Taskm taskm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Taskm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from taskm where status = 2 and good = 1 limit #{start},10")
	List<Taskm> ten(long start);
}
