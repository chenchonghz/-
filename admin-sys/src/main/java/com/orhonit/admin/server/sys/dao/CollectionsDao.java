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

import com.orhonit.admin.server.sys.model.Collections;

@Mapper
public interface CollectionsDao {

    @Select("select * from collections t where t.id = #{id}")
    Collections getById(Long id);

    @Delete("delete from collections where id = #{id}")
    int delete(Long id);

    @Update("update collections t set herdsmanId = #{herdsmanId}, cateId = #{cateId}, chlidrenId = #{chlidrenId} where t.id = #{id}")
    int update(Collections collections);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into collections(herdsmanId, cateId, chlidrenId) values(#{herdsmanId}, #{cateId}, #{chlidrenId})")
    int save(Collections collections);
    
    int count(@Param("params") Map<String, Object> params);

    List<Collections> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
