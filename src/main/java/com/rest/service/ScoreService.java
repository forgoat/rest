package com.rest.service;

import com.rest.dao.*;
import com.rest.entity.*;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

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
    @Autowired
    private AttendanceDao attendanceDao;

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
     * 按轮次查询讨论课成绩
     * @param seminarScoreList
     * @param roundId
     * @param courseId
     * @param teamId
     * @return
     */
    public List<SeminarScore> querySeminarScoreByRoundId(List<SeminarScore> seminarScoreList,Long roundId,Long courseId,Long teamId){
        List<Seminar> seminarList=seminarDao.findByCourseIdAndRoundId(courseId,roundId);//一轮的seminar
        List<Long> klassIdList=klassTeamDao.findByTeamId(teamId);
        List<Long> klassSeminarIdList=new ArrayList<>();
        for(Seminar seminar:seminarList){
            for(Long klassId:klassIdList){
                klassSeminarIdList.add(klassSeminarDao.queryKlassSeminarIdByKlassIdAndSeminarId(klassId,seminar.getId()));
            }
        }
        List<SeminarScore> seminarScoreList1=new ArrayList<>();
        for(Long klassSeminarId:klassSeminarIdList){
            for(SeminarScore seminarScore:seminarScoreList){
                if(klassSeminarId==seminarScore.getKlassSeminarId()) seminarScoreList1.add(seminarScore);
            }
        }
        return seminarScoreList1;
    }


    /**
     * 查询ScorePage所需信息
     * @param courseId
     * @param studentId
     * @param roundSerial
     * @return
     */
    public ScorePage queryScorePage(Long courseId,Long studentId,Integer roundSerial,Long teamId){

        List<SeminarScore> seminarScoreList=queryByCourseIdStudentId(courseId,studentId);
        Long roundId=roundDao.queryRoundIdByCourseIdAndRoundSerial(courseId,roundSerial);
        List<Seminar> seminarList=seminarDao.findByCourseIdAndRoundId(courseId,roundId);
        List<SeminarScore> seminarScoreList1=querySeminarScoreByRoundId(seminarScoreList,roundId,courseId,teamId);

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

    /**
     * 修改讨论课展示成绩
     * @param klassSeminarId
     * @param teamId
     * @param presentationScore
     * @return
     */
    public int updateSeminarScore(Long klassSeminarId,Long teamId,double presentationScore){
        return seminarScoreDao.updateSeminarPresentationScore(klassSeminarId,teamId,presentationScore);
    }

    /**
     * 修改讨论课报告成绩
     * @param klassSeminarId
     * @param teamId
     * @param reportScore
     * @return
     */
    public int updateReportScore(Long klassSeminarId,Long teamId,double reportScore){
        return seminarScoreDao.updateSeminarReportScore(klassSeminarId,teamId,reportScore);
    }

    /**
     * 直接查轮次的总分
     * @param roundId
     * @param teamId
     * @return
     */
    public Double roundTotalScore(Long roundId,Long teamId){
        RoundScore roundScore=roundScoreDao.findRoundByRoundIdAndTeamId(roundId,teamId);
        Double score=roundScore.getTotalScore();
        return score;
    }

    /**
     * 查轮次分数
     * @param courseId
     * @param roundId
     * @param teamId
     * @return
     */
    public Double findRoundScore(Long courseId,Long roundId,Long teamId){
        RoundScore roundScore=roundScoreDao.findRoundByRoundIdAndTeamId(roundId,teamId);
        Double score=roundScore.getTotalScore();
        if(score==null){
            Round round=roundDao.find(roundId);
            Double pScore=new Double(0),rScore=new Double(0),qScore=new Double(0);
            Integer presentationScoreMethod=round.getPresentationScoreMethod();
            Integer reportScoreMethod=round.getReportScoreMethod();
            Integer questionScoreMethod=round.getQuestionScoreMethod();
            System.out.println(presentationScoreMethod+reportScoreMethod+questionScoreMethod);
            Long klassId=findKlassForTeam(courseId,teamId);
            Integer enrollNumber=klassRoundDao.findByRoundIdAndClassId(roundId,klassId);
            List<Seminar> seminars=seminarDao.findByRoundId(roundId);
            Integer num=new Integer(0);
            List<Double> questionScores=new ArrayList<Double>();
            List<Double> presentationScores=new ArrayList<Double>();
            List<Double> reportScores=new ArrayList<Double>();
            for (Seminar seminar:seminars){
                Long seminarId=seminar.getId();
                Long klassSeminarId=klassSeminarDao.findByKlassIdAndSeminarId(klassId,seminarId).getId();
                List<Double> doubles=questionDao.questionScores(klassSeminarId,teamId);
                for (Double d:doubles){
                    questionScores.add(d);
                }
                Attendance attendance=attendanceDao.queryByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
                if(attendance!=null){
                    num+=1;
                    SeminarScore seminarScore=seminarScoreDao.findByTeamIdAndSeminarId(teamId,klassSeminarId);
                    Double presentationScore=seminarScore.getPresentationScore();
                    Double reportScore=seminarScore.getReportScore();
                    if(presentationScore!=null){
                        presentationScores.add(presentationScore);
                    }
                    if(reportScore!=null){
                        reportScores.add(reportScore);
                    }
                }
            }
            if(num<enrollNumber){
                score=new Double(0);
            }
            else {
                if(presentationScoreMethod==1){
                    pScore=presentationScores.get(0);
                    for(int i=1;i<presentationScores.size();i++){
                        if(presentationScores.get(i)>pScore){
                            pScore=presentationScores.get(i);
                        }
                    }
                }
                else {
                    pScore=new Double(0);
                    for(Double d:presentationScores){
                        pScore+=d;
                    }
                    pScore/=presentationScores.size();
                }
                if(reportScoreMethod==1){
                    rScore=reportScores.get(0);
                    for (int i=1;i<reportScores.size();i++){
                        if(reportScores.get(i)>rScore){
                            rScore=reportScores.get(i);
                        }
                    }
                }
                else {
                    rScore=new Double(0);
                    for (Double d:reportScores){
                        rScore+=d;
                    }
                    rScore/=reportScores.size();
                }
                if(questionScoreMethod==1){
                    qScore=questionScores.get(0);
                    for(int i=1;i<questionScores.size();i++){
                        if(questionScores.get(i)>qScore){
                            qScore=questionScores.get(i);
                        }
                    }
                }
                else {
                    qScore=new Double(0);
                    for(Double d:questionScores){
                        qScore+=d;
                    }
                    qScore/=questionScores.size();
                }
            }
            score=(pScore+rScore+qScore)/3;

        }
        return score;
    }
   public int updateSeminarScore(Long klassSeminarId,Long teamId,Double presentationScore,Double questionScore,Double reportScore){
        return seminarScoreDao.updateSeminarScore(klassSeminarId,teamId,presentationScore,questionScore,reportScore);
   }

    /**
     * 查找每个队伍的所在班级
     * @param courseId
     * @param teamId
     * @return
     */
   public Long findKlassForTeam(Long courseId,Long teamId){
        List<Klass> klassList=klassDao.findByCourseId(courseId);
        List<Long> klassTeamList=klassTeamDao.findByTeamId(teamId);
        Long classId=new Long(0);
        for (Klass klass:klassList){
            for(Long id:klassTeamList){
                if(klass.getId().equals(id)){
                    classId=id;
                }
            }
        }
        return classId;
   }
   public List<Double> findQuestionScore(Long klassSeminarId,Long teamId){
       return questionDao.questionScores(klassSeminarId,teamId);
   }
}
