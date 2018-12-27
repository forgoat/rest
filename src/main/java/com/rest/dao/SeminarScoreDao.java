package com.rest.dao;

import com.rest.entity.SeminarScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface SeminarScoreDao {
    public int save(SeminarScore seminarScore);
    public SeminarScore findByTeamIdAndSeminarId(@Param("teamId") Long teamId, @Param("seminarId") Long seminarId);
    public List<SeminarScore> findAllByKlassSeminarId(Long classSeminarId);
    public int updateSeminarQuestionScore(@Param("klassSeminarId") Long klassSeminarId, @Param("teamId") Long teamId, @Param("questionScore") double questionScore);
}
