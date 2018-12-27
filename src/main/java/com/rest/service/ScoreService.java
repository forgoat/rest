package com.rest.service;

import com.rest.dao.QuestionDao;
import com.rest.dao.ScoreDao;
import com.rest.dao.SeminarScoreDao;
import com.rest.entity.Question;
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
    @Autowired
    private QuestionDao questionDao;

    public int saveSeminarScore(SeminarScore seminarScore){
        return  seminarScoreDao.save(seminarScore);
    }
    public SeminarScore findByTeamIdAndSeminarId(Long teamId,Long seminarId){
        return seminarScoreDao.findByTeamIdAndSeminarId(teamId,seminarId);
    }
    public List<SeminarScore> findByKlassSeminarId(Long classSeminarId){
        return seminarScoreDao.findAllByKlassSeminarId(classSeminarId);
    }
    public int updateSeminarQuestionScore(Long klassSeminarId,Long teamId,Integer questionScore){
        return seminarScoreDao.updateSeminarQuestionScore(klassSeminarId,teamId,questionScore);
    }

    /**
     * 给提问打分并记入seminarScore
     * @param questionId
     * @param questionScore
     * @return
     */
    public int gradeQuestion(Long questionId,double questionScore){
        Question question=questionDao.findQuestionById(questionId);
        Long klassSeminarId=question.getKlassSeminarId();
        Long teamId=question.getTeamId();
        if(questionDao.gradeQuestion(questionId,questionScore)==1){
            if(seminarScoreDao.updateSeminarQuestionScore(klassSeminarId,teamId,questionScore)==1)
                return 1;
            else {
                questionDao.gradeQuestion(questionId,0);
                return 0;
            }
        }
        else {
            return 0;
        }
    }

    public Question findQuestionById(Long id){
        return questionDao.findQuestionById(id);
    }
}
