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

import com.orhonit.admin.server.sys.model.Videoconnect;

@Mapper
public interface VideoconnectDao {

    @Select("select * from videoconnect t where t.id = #{id}")
    Videoconnect getById(Long id);

    @Delete("delete from videoconnect where id = #{id}")
    int delete(Long id);

    @Update("update videoconnect t set hid = #{hid}, eid = #{eid}, roomid = #{roomid}, url = #{url} where t.id = #{id}")
    int update(Videoconnect videoconnect);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into videoconnect(hid, eid, roomid, url) values(#{hid}, #{eid}, #{roomid}, #{url})")
    int save(Videoconnect videoconnect);
    
    int count(@Param("params") Map<String, Object> params);

    List<Videoconnect> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
}
