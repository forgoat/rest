package com.rest.service;

import com.rest.dao.*;
import com.rest.entity.*;
import org.apache.ibatis.jdbc.Null;
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
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeamDao teamDao;

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
        //一个team包括主从课程的班级
        List<Long> klassIdList=klassTeamDao.findByTeamId(teamId);
        System.out.println("klassIdList:"+klassIdList);
        //一个课程里所有的班级
        List<Klass> klassList=klassDao.findByCourseId(courseId);
        System.out.println("klassList:"+klassList);
        Long klassId=null;
        //查找出一个相同的klassId
        for(Long a: klassIdList){
            System.out.println("a:"+a);
            for(Klass b:klassList){
                System.out.println("b:"+b);
                if(a.equals(b.getId())){
                    klassId=a;
                    System.out.println("klassId: "+a);
                    break;
                }
            }
            if(klassId!=null) break;
        }
        //找seminarId
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
        System.out.println("$ teamId:"+teamId);
        List<Long> klassIdList=klassTeamDao.findByTeamId(teamId);
        System.out.println("$ klassIdList:"+klassIdList);
        List<Klass> klassList=klassDao.findByCourseId(courseId);
        System.out.println("$ klassList:"+klassList);
        Long klassId=null;
        for(Long a: klassIdList){
            for(Klass b:klassList){
                if(a.equals(b.getId())&&a!=null&&b!=null){
                    klassId=a;
                    System.out.println("$ klassId:"+klassId);
                    break;
                }
            }
        }

        List<KlassRound> klassRoundList=klassRoundDao.findByKlassId(klassId);
        System.out.println("$ klassRoundList:"+klassRoundList);
        if(klassRoundList.size()!=0&&klassRoundList!=null){
            List<RoundScore> roundScoreList=new ArrayList<>();
            for(KlassRound klassRound:klassRoundList){
                if(klassRound!=null)
                    roundScoreList.add(roundScoreDao.queryByRoundIdTeamId(klassRound.getRoundId(),teamId));
                System.out.println("$ roundScoreList"+roundScoreList);
            }
        return roundScoreList;
        }
        else{
            System.out.println("$ return null");
            return null;
        }

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
        System.out.println("seminarList"+seminarList);
        List<Long> klassIdList=klassTeamDao.findByTeamId(teamId);
        System.out.println("klassIdList"+klassIdList);
        List<Long> klassSeminarIdList=new ArrayList<>();
        for(Seminar seminar:seminarList){
            System.out.println("for循环：seminar: "+seminar);
            for(Long klassId:klassIdList){
                System.out.println("klassId: "+klassId);
                klassSeminarIdList.add(klassSeminarDao.queryKlassSeminarIdByKlassIdAndSeminarId(klassId,seminar.getId()));
                System.out.println("klassSeminarIdList: "+klassSeminarIdList);
            }
        }
        List<SeminarScore> seminarScoreList1=new ArrayList<>();
        for(Long klassSeminarId:klassSeminarIdList){
            for(SeminarScore seminarScore:seminarScoreList) {
                System.out.println("klassSeminarId:" + klassSeminarId + " " + seminarScore);
                if (seminarScore != null&&klassSeminarId!=null) {
                    if (klassSeminarId.equals(seminarScore.getKlassSeminarId())) {
                        seminarScoreList1.add(seminarScore);
                        System.out.println("+seminarScoreList1: "+seminarScoreList1);
                    }
                }
                else continue;
            }
        }
        System.out.println("seminarScoreList1: "+seminarScoreList1);
        return seminarScoreList1;
    }


    /**
     * 查询ScorePage所需信息
     * @param courseId
     * @param studentId
     * @param roundSerial
     * @return
     */
    public ScorePage queryScorePage(Long courseId,Long studentId,Integer roundSerial){
        Long teamId=teamStudentDao.findByStudentId(studentId);
        System.out.println("teamId: "+teamId);
        //！！！SeminarScore不能用课程查 因为要考虑从课程 需要用klassId查找
        List<SeminarScore> seminarScoreList=queryByCourseIdStudentId(courseId,studentId);
        System.out.println("seminarScoreList: "+seminarScoreList);
        Long roundId=roundDao.queryRoundIdByCourseIdAndRoundSerial(courseId,roundSerial);
        System.out.println("roundId: "+roundId);
        List<Seminar> seminarList=seminarDao.findByCourseIdAndRoundId(courseId,roundId);
        System.out.println("seminarList: "+seminarList);
        List<SeminarScore> seminarScoreList1=querySeminarScoreByRoundId(seminarScoreList,roundId,courseId,teamId);
        System.out.println("seminarScoreList1: "+seminarScoreList1);


        ScorePage scorePage=new ScorePage();
        if(roundId!=null)
        scorePage.setRoundId(roundId);
        else scorePage.setRoundId(null);
        System.out.println("scorePage: "+scorePage);
        //RoundScore为roundSerial的
        if(roundSerial!=null)
            scorePage.setRoundSerial(roundSerial);
        else scorePage.setRoundSerial(null);
        System.out.println("scorePage: "+scorePage);

        if(queryRoundScore(courseId,studentId,roundSerial)!=null&&!queryRoundScore(courseId,studentId,roundSerial).equals(null))
            scorePage.setRoundScore(queryRoundScore(courseId,studentId,roundSerial));
        else scorePage.setRoundScore(null);
        System.out.println("scorePage: "+scorePage);

        if(seminarList.size()!=0&&seminarList!=null)
            scorePage.setSeminarList(seminarList);
        else scorePage.setRoundScore(null);
        System.out.println("scorePage: "+scorePage);

        if(seminarScoreList1.size()!=0&&seminarScoreList1!=null)
            scorePage.setSeminarScoreList(seminarScoreList1);
        else scorePage.setRoundScore(null);
        System.out.println("scorePage: "+scorePage);
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
        if(queryByCourseId(courseId,studentId)!=null){
            List<RoundScore> roundScoreList=queryByCourseId(courseId,studentId);
            if(roundSerial!=null) {
                if (roundScoreList.get(roundSerial) != null && roundScoreList.size() != 0)
                    return roundScoreList.get(roundSerial);
                else return null;
            }
        }
        return null;
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
            Course course=courseDao.findById(courseId);
            score=pScore*course.getPresentationPercentage()+rScore*course.getReportPercentage()+qScore*course.getQuestionPercentage();
            score/=100;
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

//    public List<RoundScorePage> teacherFindScore(Long courseId){
//        List<RoundScorePage> roundScorePageList=new ArrayList<>();
//        List<Round> roundList=roundDao.findByCourseId(courseId);
//        if (roundList.isEmpty()){
//            System.out.println("No round");
//            return roundScorePageList;
//        }
//        else {
//            Long mainCourseId=new Long(0);
//            Course course=courseDao.findById(courseId);
//            System.out.println(course.toString());
//            if (course.getTeamMainCourseId()==null){
//                System.out.println("主课程");
//                mainCourseId=courseId;
//            }
//            else {
//                System.out.println("从课程");
//                mainCourseId=course.getTeamMainCourseId();
//            }
//            System.out.println("现在的课程的id "+mainCourseId+" 输入的课程id "+courseId);
//            List<Team> teamList=teamDao.findByCourseId(courseId);
//            if (teamList.isEmpty()){
//                System.out.println("没有小组");
//                return roundScorePageList;
//            }
//            else {
//                System.out.println("有轮次又有小组");
//                for(Round round:roundList){
//                    RoundScorePage roundScorePage=new RoundScorePage();
//                    roundScorePage.setRoundId(round.getId());
//                    roundScorePage.setRoundSerial(round.getRoundSerial());
//                    List<TeamScore> teamScores=new ArrayList<TeamScore>();
//                    for (Team team:teamList){
//                        ScorePage scorePage=queryScorePage(courseId,team.getLeaderId(),round.getRoundSerial());
//                        TeamScore teamScore=new TeamScore(scorePage);
//                        teamScores.add(teamScore);
//                    }
//                    roundScorePage.setTeamScoreList(teamScores);
//                    roundScorePageList.add(roundScorePage);
//                }
//                return roundScorePageList;
//            }
//        }
//    }

    public SeminarScoreInfo findSeminarScoreInfo(Long teamId,Long klassSeminarId){
       SeminarScore seminarScore=seminarScoreDao.findByTeamIdAndSeminarId(teamId,klassSeminarId);
       if (seminarScore==null){
           SeminarScoreInfo seminarScoreInfo=new SeminarScoreInfo();
           return seminarScoreInfo;
       }
       SeminarScoreInfo seminarScoreInfo=new SeminarScoreInfo(seminarScore);
       KlassSeminar klassSeminar=klassSeminarDao.findKlassSeminarById(klassSeminarId);
       Long seminarId=klassSeminar.getSeminarId();
       Seminar seminar=seminarDao.findById(seminarId);
       seminarScoreInfo.setSeminarName(seminar.getSeminarName());
       return seminarScoreInfo;
    }


    public RoundScoreInfo findRoundScoreInfo(Long courseId){
       RoundScoreInfo roundScoreInfo=new RoundScoreInfo();
       Course course=courseDao.findById(courseId);
       if (course==null){
         //  System.out.println("No Course");
           return roundScoreInfo;
       }
       List<Round> roundList=roundDao.findByCourseId(courseId);
       if (roundList.isEmpty()){
          // System.out.println("No Round");
           return roundScoreInfo;
       }
       roundScoreInfo.setRoundList(roundList);
       List<Klass> klassList=klassDao.findByCourseId(courseId);
       if (klassList.isEmpty()){
          // System.out.println("No class");
           return roundScoreInfo;
       }
       roundScoreInfo.setKlassList(klassList);
       Long mainCourseId=new Long(0);
       if (course.getTeamMainCourseId()==null){
           mainCourseId=courseId;
       }
       else {
           mainCourseId=course.getTeamMainCourseId();
       }
       List<Team> teamList=teamDao.findByCourseId(mainCourseId);
       if (teamList.isEmpty()){
          // System.out.println("No Team");
           return roundScoreInfo;
       }
       roundScoreInfo.setTeamList(teamList);
       return roundScoreInfo;
    }

    public TeamRoundScore findTeamRoundScore(Long courseId,Team team,Round round,List<Klass> klassList){
       TeamRoundScore teamRoundScore=new TeamRoundScore();
       teamRoundScore.setKlassSerial(team.getKlassSerial());
       teamRoundScore.setTeamSerial(team.getTeamSerial());
       List<Seminar> seminarList=seminarDao.findByRoundId(round.getId());
       if (seminarList.isEmpty()){
           //System.out.println("No seminar");
           return teamRoundScore;
       }
       Long klassId=new Long(0);
       List<Long> klassIdList=klassTeamDao.findByTeamId(team.getId());
       for (Long k:klassIdList){
           for(Klass klass:klassList){
               if (k.equals(klass.getId())){
                   klassId=k;
                   break;
               }
           }
       }
       System.out.println(klassId);
       if (klassId==0){
           return teamRoundScore;
       }
       List<SeminarScoreInfo> seminarScoreInfos=new ArrayList<SeminarScoreInfo>();
       for(Seminar seminar:seminarList){
           KlassSeminar klassSeminar=klassSeminarDao.findByKlassIdAndSeminarId(klassId,seminar.getId());
           if (klassSeminar==null){

           }
           else {
               SeminarScoreInfo seminarScoreInfo = findSeminarScoreInfo(team.getId(), klassSeminar.getId());
               if (seminarScoreInfo.getKlassSeminarId()==null){
                   //System.out.println(seminarScoreInfo.toString());
               }
               else {
                   //System.out.println(seminarScoreInfo.toString());
                   seminarScoreInfos.add(seminarScoreInfo);
               }
           }
       }
       teamRoundScore.setSeminarScoreInfos(seminarScoreInfos);
       teamRoundScore.setRoundScore(findRoundScore(courseId,round.getId(),team.getId()));
       return teamRoundScore;
    }
}
