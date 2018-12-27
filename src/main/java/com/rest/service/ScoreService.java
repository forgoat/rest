package com.rest.service;

import com.rest.dao.*;
import com.rest.entity.*;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

    @Autowired
    private SeminarScoreDao seminarScoreDao;
    @Autowired
    private SeminarDao seminarDao;
    @Autowired
    private KlassSeminarDao klassSeminarDao;
    @Autowired
    private TeamStudentDao teamStudentDao;
    @Autowired
    private KlassTeamDao klassTeamDao;
    @Autowired
    private KlassDao klassDao;
    @Autowired
    private RoundDao roundDao;
    @Autowired
    private KlassRoundDao klassRoundDao;
    @Autowired
    private RoundScoreDao roundScoreDao;
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

    /**
     * 此课程所有的轮次序列号 例如：第几次讨论课
     * @param courseId
     * @return
     */
    public List<Integer> queryAllRoundSerialByCourseId(Long courseId){
        return roundDao.queryAllRoundSerialByCourseId(courseId);
    }

    /**
     * 查找讨论课成绩
     * @param courseId
     * @param studentId
     * @return
     */
    public List<SeminarScore> queryByCourseIdStudentId(Long courseId,Long studentId){
        Long teamId=teamStudentDao.findByStudentId(studentId);
        System.out.println("teamId:"+teamId);
        List<Long> klassIdList=klassTeamDao.findByTeamId(teamId);
        System.out.println("klassIdList:"+klassIdList);
        List<Klass> klassList=klassDao.findByCourseId(courseId);
        System.out.println("klassList:"+klassList);
        Long klassId=null;
        for(Long a: klassIdList){
            System.out.println("a:"+a);
            for(Klass b:klassList){
                System.out.println("b:"+b);
                if(a==b.getId()){
                    klassId=a;
                    System.out.println("klassId"+a);
                    break;
                }
            }
            if(klassId!=null) break;
        }
        List<KlassSeminar> klassSeminarList=klassSeminarDao.queryByKlassId(klassId);
        System.out.println("klassSeminarList: "+klassSeminarList);
        List<SeminarScore> seminarScoreList=new ArrayList<>();
        for(KlassSeminar klassSeminar:klassSeminarList) {
            seminarScoreList.add(seminarScoreDao.queryByKlassSeminarIdAndTeamId(klassSeminar.getId(), teamId));
            System.out.println("===="+seminarScoreDao.queryByKlassSeminarIdAndTeamId(klassSeminar.getId(), teamId));
        }
        System.out.println("seminarScoreList: "+seminarScoreList);
        return seminarScoreList;
    }

    /**
     * 查找轮次成绩
     * @param courseId
     * @param studentId
     * @return
     */
    public List<RoundScore> queryByCourseId(Long courseId,Long studentId){
        Long teamId=teamStudentDao.findByStudentId(studentId);
        List<Long> klassIdList=klassTeamDao.findByTeamId(teamId);
        List<Klass> klassList=klassDao.findByCourseId(courseId);
        Long klassId=null;
        for(Long a: klassIdList){
            for(Klass b:klassList){
                if(a==b.getId()){
                    klassId=a;
                    break;
                }
            }
        }
        List<KlassRound> klassRoundList=klassRoundDao.findByKlassId(klassId);
        List<RoundScore> roundScoreList=new ArrayList<>();
        for(KlassRound klassRound:klassRoundList){
            roundScoreList.add(roundScoreDao.queryByRoundIdTeamId(klassRound.getRoundId(),teamId));
        }
        return roundScoreList;
    }

    /**
     * 查询ScorePage所需信息
     * @param courseId
     * @param studentId
     * @param roundSerial
     * @return
     */
    public ScorePage queryScorePage(Long courseId,Long studentId,Integer roundSerial){

        List<SeminarScore> seminarScoreList=queryByCourseIdStudentId(courseId,studentId);
        Long roundId=roundDao.queryRoundIdByCourseIdAndRoundSerial(courseId,roundSerial);
        List<Seminar> seminarList=seminarDao.findByCourseIdAndRoundId(courseId,roundId);

        ScorePage scorePage=new ScorePage();
        scorePage.setRoundId(roundId);
        //RoundScore为roundSerial的
        scorePage.setRoundScore(queryRoundScore(courseId,studentId,roundSerial));
        scorePage.setRoundSerial(roundSerial);
        scorePage.setSeminarList(seminarList);
        scorePage.setSeminarScoreList(seminarScoreList);
        return scorePage;
    }

    /**
     * 按roundSerial查找RoundScore
     * @param courseId
     * @param studentId
     * @param roundSerial
     * @return
     */
    public RoundScore queryRoundScore(Long courseId,Long studentId,Integer roundSerial){
        List<RoundScore> roundScoreList=queryByCourseId(courseId,studentId);
        return roundScoreList.get(roundSerial);
    }

    public Question findQuestionById(Long id){
        return questionDao.findQuestionById(id);
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

}
