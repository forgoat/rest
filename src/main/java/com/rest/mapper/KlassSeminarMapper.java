package com.rest.mapper;

import com.rest.po.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
@Mapper
public interface KlassSeminarMapper {
    public List<KlassSeminar> findBySeminar(Long seminarId);
    public KlassSeminar findKlassSeminarById(Long klassSeminarId);
    public int save(KlassSeminar klassSeminar);
    public int startSeminar(Long klassSeminarId);
    public int delete(Long id);
    public int deleteBySeminarId(Long seminarId);
    public KlassSeminar findByKlassIdAndSeminarId(@Param("klassId") Long klassId,@Param("seminarId") Long seminarId);
    public int changeStatus(@Param("klassSeminarId") Long klassSeminarId, @Param("status") Integer status);
    public int changeddl(@Param("seminarId")Long seminarId, @Param("classId")Long classId, @Param("reportDdl")Date reportDdl);
    Long queryKlassSeminarIdByKlassIdAndSeminarId(@Param("klassId")Long klassId,@Param("seminarId")Long seminarId);
    List<KlassSeminar> queryByKlassId(@Param("klassId") Long klassId);
}
