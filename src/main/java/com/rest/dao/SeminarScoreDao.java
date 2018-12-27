package com.rest.dao;

import com.rest.entity.SeminarScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeminarScoreDao {
     int save(SeminarScore seminarScore);
     SeminarScore findByTeamIdAndSeminarId(@Param("teamId") Long teamId, @Param("seminarId") Long seminarId);
     List<SeminarScore> findAllByKlassSeminarId(Long classSeminarId);
     SeminarScore queryByKlassSeminarIdAndTeamId(@Param("klassSeminarId")Long klassSeminarId,@Param("teamId")Long teamId);

}
