package com.rest.dao;

import com.rest.entity.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
@Mapper
public interface KlassSeminarDao {
    public List<KlassSeminar> findBySeminar(Long seminarId);
    public KlassSeminar findKlassSeminarById(Long klassSeminarId);
    public int save(KlassSeminar klassSeminar);
    public int startSeminar(Long klassSeminarId);
    public int delete(Long id);
    public int deleteBySeminarId(Long seminarId);
    public KlassSeminar findByKlassIdAndSeminarId(@Param("klassId") Long klassId,@Param("seminarId") Long seminarId);
    public int changeStatus(@Param("seminarId") Long seminarId, @Param("classId") Long classId, @Param("status") Integer status);
    public int changeddl(@Param("seminarId")Long seminarId, @Param("classId")Long classId, @Param("reportDdl")Date reportDdl);
    Long queryKlassSeminarIdByKlassIdAndSeminarId(@Param("klassId")Long klassId,@Param("seminarId")Long seminarId);
    List<KlassSeminar> queryByKlassId(@Param("klassId") Long klassId);
}
