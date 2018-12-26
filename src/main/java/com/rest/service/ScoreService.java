package com.rest.service;

import com.rest.dao.ScoreDao;
import com.rest.dao.SeminarScoreDao;
import com.rest.entity.Score;
import com.rest.entity.SeminarScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private SeminarScoreDao seminarScoreDao;

    public int saveSeminarScore(SeminarScore seminarScore){
        return  seminarScoreDao.save(seminarScore);
    }
    public SeminarScore findByTeamIdAndSeminarId(Long teamId,Long seminarId){
        return seminarScoreDao.findByTeamIdAndSeminarId(teamId,seminarId);
    }
    public List<SeminarScore> findByKlassSeminarId(Long classSeminarId){
        return seminarScoreDao.findAllByKlassSeminarId(classSeminarId);
    }
}
