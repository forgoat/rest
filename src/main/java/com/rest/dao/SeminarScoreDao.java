package com.rest.dao;

import com.rest.entity.SeminarScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeminarScoreDao {
    public int save(SeminarScore seminarScore);
    public SeminarScore findByTeamIdAndSeminarId(@Param("teamId") Long teamId, @Param("seminarId") Long seminarId);
    public List<SeminarScore> findAllByKlassSeminarId(Long classSeminarId);
}
