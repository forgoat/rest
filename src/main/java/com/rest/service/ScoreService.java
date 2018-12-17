package com.rest.service;

import com.rest.dao.ScoreDao;
import com.rest.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    public List<Score>queryScore(BigInteger course_id,BigInteger student_id,BigInteger seminar_id,BigInteger round_id){
        return scoreDao.queryScore(course_id,student_id,seminar_id,round_id);
    }
}
