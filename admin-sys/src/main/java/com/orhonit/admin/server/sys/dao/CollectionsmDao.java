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

import com.orhonit.admin.server.sys.model.Collectionsm;

@Mapper
public interface CollectionsmDao {

    @Select("select * from collectionsm t where t.id = #{id}")
    Collectionsm getById(Long id);

    @Delete("delete from collectionsm where id = #{id}")
    int delete(Long id);

    @Update("update collectionsm t set herdsmanId = #{herdsmanId}, cateId = #{cateId}, chlidrenId = #{chlidrenId} where t.id = #{id}")
    int update(Collectionsm collectionsm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into collectionsm(herdsmanId, cateId, chlidrenId) values(#{herdsmanId}, #{cateId}, #{chlidrenId})")
    int save(Collectionsm collectionsm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Collectionsm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from collectionsm where herdsmanId = #{arg0} and cateId= #{arg1}")
	List<Collectionsm> Applook(Long id, Integer cateId);
}
