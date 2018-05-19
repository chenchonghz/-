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

import com.orhonit.admin.server.sys.model.Drug;

@Mapper
public interface DrugDao {

    @Select("select * from drug t where t.id = #{id}")
    Drug getById(Long id);

    @Delete("delete from drug where id = #{id}")
    int delete(Long id);

    @Update("update drug t set categoryId = #{categoryId}, title = #{title}, titlemeng = #{titlemeng}, content = #{content}, contentmeng = #{contentmeng}, drugUrl = #{drugUrl}, uid = #{uid}, number = #{number}, status = #{status} where t.id = #{id}")
    int update(Drug drug);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into drug(categoryId, title, titlemeng, content, contentmeng, drugUrl, uid, number, status) values(#{categoryId}, #{title}, #{titlemeng}, #{content}, #{contentmeng}, #{drugUrl}, #{uid}, #{number}, #{status})")
    int save(Drug drug);
    
    int count(@Param("params") Map<String, Object> params);

    List<Drug> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Select("select * from drug where uid=#{uid}")
	List<Drug> getByUid(Long uid);
    @Select("select * from drug t where t.uid = #{did} and t.status = 0")
	List<Drug> getByDid(Integer did);
    @Update("update drug t set t.number = t.number - 1 where id = #{drugId} ")
	void updateNumber(Integer drugId);
    @Update("update drug t set t.status = #{arg1} where t.id = #{arg0}")
	void updateStatus(Integer id, Integer status);
    @Update("update drug t set t.number = #{arg1} where t.id = #{arg0}")
	void updateByNumber(Integer id, Integer number);
}
