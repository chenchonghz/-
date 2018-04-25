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

import com.orhonit.admin.server.sys.model.Expertinfo;

@Mapper
public interface ExpertinfoDao {

    @Select("select * from expertinfo t where t.id = #{id}")
    Expertinfo getById(Long id);

    @Select("select * from expertinfo t where t.uid = #{id}")
    Expertinfo ById(Long id);

    @Select("select t.*,a.name as educationName,b.name as educationMengName from expertinfo t left join education a on t.education=a.id left join educationm b on t.educationMeng=b.id where t.uid = #{id}")
    Expertinfo ByUid(int id);
    
    @Delete("delete from expertinfo where id = #{id}")
    int delete(Long id);

    @Update("update expertinfo t set uid = #{uid}, expertCertificate = #{expertCertificate}, education = #{education}, educationMeng = #{educationMeng}, personal = #{personal}, personalMeng = #{personalMeng}, status = #{status}, state = #{state}, dealingProblems = #{dealingProblems}, expertise = #{expertise}, expertiseMeng = #{expertiseMeng}, activity = #{activity}, videoDiagnosis = #{videoDiagnosis}, phoneDiagnosis = #{phoneDiagnosis}, address = #{address}, addressMeng = #{addressMeng}, sex = #{sex}, headerUrl = #{headerUrl}, createTime = #{createTime}, updateTime = #{updateTime}, cardNumber = #{cardNumber}, name = #{name}, nameMeng = #{nameMeng},reason = #{reason} where t.id = #{id}")
    int update(Expertinfo expertinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into expertinfo(uid, expertCertificate, education, educationMeng, personal, personalMeng, status, state, dealingProblems, expertise, expertiseMeng, activity, videoDiagnosis, phoneDiagnosis, address, addressMeng, sex, headerUrl, createTime, updateTime, cardNumber, name, nameMeng, reason) values(#{uid}, #{expertCertificate}, #{education}, #{educationMeng}, #{personal}, #{personalMeng}, #{status}, #{state}, #{dealingProblems}, #{expertise}, #{expertiseMeng}, #{activity}, #{videoDiagnosis}, #{phoneDiagnosis}, #{address}, #{addressMeng}, #{sex}, #{headerUrl}, #{createTime}, #{updateTime}, #{cardNumber}, #{name}, #{nameMeng}, #{reason})")
    int save(Expertinfo expertinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<Expertinfo> list(@Param("params") Map<String, Object> params, @Param("start") Integer start, @Param("length") Integer length);
   
    @Update("update expertinfo t set state = #{arg1} where t.uid = #{arg0}")
	void updateState(int id,int i);
    
    @Select("select * from expertinfo t where t.status=1 order by t.dealingProblems desc limit 0,3")
	List<Expertinfo> three();
    
    @Select("select * from expertinfo where status = 1 order by state asc, id desc limit #{start},10")
	List<Expertinfo> ten(long start);
    @Update("update expertinfo t set videoDiagnosis = videoDiagnosis + 1 where t.uid = #{eid}")
	void updatevideoDiagnosis(Integer eid);

    @Update("update expertinfo set expertCertificate=#{expertCertificate},education=#{education},educationMeng=#{educationMeng},personal=#{personal},personalMeng=#{personalMeng},expertise=#{expertise},expertiseMeng=#{expertiseMeng},address=#{address},addressMeng=#{addressMeng},sex=#{sex},headerUrl=#{headerUrl},cardNumber=#{cardNumber},name=#{name},nameMeng=#{nameMeng},status=#{status},updateTime=#{updateTime} where id = #{id}")
	void AppUpdate(Expertinfo expertinfo);
    @Select("select * from expertinfo where status = 1")
	List<Expertinfo> AppList();
}
