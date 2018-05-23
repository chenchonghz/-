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

import com.orhonit.admin.server.sys.model.Introduce;

@Mapper
public interface IntroduceDao {

    @Select("select * from introduce t where t.id = #{id}")
    Introduce getById(Long id);

    @Delete("delete from introduce where id = #{id}")
    int delete(Long id);

    @Update("update introduce t set title = #{title}, introduce = #{introduce} where t.id = #{id}")
    int update(Introduce introduce);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into introduce(title, introduce) values(#{title}, #{introduce})")
    int save(Introduce introduce);
    
    int count(@Param("params") Map<String, Object> params);

    List<Introduce> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from introduce")
	List<Introduce> getAll();
}
