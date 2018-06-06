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

import com.orhonit.admin.server.sys.model.Commentm;

@Mapper
public interface CommentmDao {

    @Select("select * from commentm t where t.id = #{id}")
    Commentm getById(Long id);

    @Delete("delete from commentm where id = #{id}")
    int delete(Long id);

    @Update("update commentm t set herdsmanId = #{herdsmanId}, content = #{content}, status = #{status}, cateId = #{cateId}, chlidrenId = #{chlidrenId} where t.id = #{id}")
    int update(Commentm commentm);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into commentm(herdsmanId, content, status, cateId, chlidrenId) values(#{herdsmanId}, #{content}, #{status}, #{cateId}, #{chlidrenId})")
    int save(Commentm commentm);
    
    int count(@Param("params") Map<String, Object> params);

    List<Commentm> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
    @Update("update commentm set status = 1 where id = #{id}")
	void examine(Integer id);
    @Select("select * from commentm where status = 1")
	List<Commentm> getList();
    @Select("select * from commentm where cateId = #{arg0} and chlidrenId = #{arg1} and status =1")
	List<Commentm> getCBycid(Integer cateId, Integer childrenId);
}
