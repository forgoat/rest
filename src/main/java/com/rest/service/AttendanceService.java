package com.rest.service;

import com.rest.mapper.*;
import com.rest.entity.*;
import com.rest.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
//public class AttendanceService {
//    @Autowired
//    private AttendanceMapper attendanceMapper;
//    @Autowired
//    private TeamMapper teamMapper;
//    @Autowired
//    private SeminarMapper seminarMapper;
//    @Autowired
//    private KlassSeminarMapper klassSeminarMapper;
//    @Autowired
//    private QuestionMapper questionMapper;
//    @Autowired
//    private StudentMapper studentMapper;
//    @Autowired
//    private TeamStudentMapper teamStudentMapper;
//    @Autowired
//    private KlassTeamMapper klassTeamMapper;
//    @Autowired
//    private KlassMapper klassMapper;
//    public List<Attendance> findAttendanceByKlassSeminarId(Long klassSeminarId){
//        return attendanceMapper.findAttendanceByKlassSeminarId(klassSeminarId);
//    }
//    public Attendance findAttendanceByKlassSeminarAndTeamId(Long klassSeminarId,Long teamId){
//        return attendanceMapper.queryByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
//    }
//    public Team findTeamByTeamId(Long teamId){
//        return teamMapper.findById(teamId);
//    }
//    public List<AttendanceInfo> findAttendanceInfoByKlassSeminarId(Long klassSeminarId){
//        List<Attendance> attendanceList= attendanceMapper.findAttendanceByKlassSeminarId(klassSeminarId);
//        List<AttendanceInfo> attendanceInfoList=new ArrayList<AttendanceInfo>();
//        for (Attendance attendance:attendanceList){
//            AttendanceInfo attendanceInfo=new AttendanceInfo(attendance);
//            Team team= teamMapper.findById(attendance.getTeamId());
//            attendanceInfo.setTeamName(team.getTeamName());
//            attendanceInfo.setTeamSerial(team.getTeamSerial());
//            attendanceInfoList.add(attendanceInfo);
//        }
//        return attendanceInfoList;
//    }
//    public AttendanceInfomation findAttendanceInformation(Long klassSeminarId){
//        List<Attendance> attendanceList= attendanceMapper.findAttendanceByKlassSeminarId(klassSeminarId);
//        List<AttendanceInfo> attendanceInfoList=new ArrayList<AttendanceInfo>();
//        Integer klassSerial=new Integer(0);
//        Seminar seminar=new Seminar();
//        for (Attendance attendance:attendanceList){
//            AttendanceInfo attendanceInfo=new AttendanceInfo(attendance);
//            KlassSeminar klassSeminar= klassSeminarMapper.findKlassSeminarById(attendance.getKlassSeminarId());
//            seminar= seminarMapper.findById(klassSeminar.getSeminarId());
//            Team team= teamMapper.findById(attendance.getTeamId());
//            klassSerial=team.getKlassSerial();
//            attendanceInfo.setTeamSerial(team.getTeamSerial());
//            attendanceInfo.setTeamName(team.getTeamName());
//            attendanceInfoList.add(attendanceInfo);
//        }
//        AttendanceInfomation attendanceInfomation=new AttendanceInfomation(attendanceInfoList);
//        attendanceInfomation.setKlassSerial(klassSerial);
//        attendanceInfomation.setMaxTeam(seminar.getMaxTeam());
//        return attendanceInfomation;
//    }
//    public int saveAttendance(Attendance attendance){
//        return attendanceMapper.saveAttendance(attendance);
//    }
//    public int deleteAttendanceById(Long attendanceId){
//        return attendanceMapper.deleteById(attendanceId);
//    }
//    public int saveQuestion(Question question){
//        return questionMapper.saveQuestion(question);
//    }
//    public List<Question> questionList(Long klassSeminarId,Long attendanceId){
//        return questionMapper.questionList(klassSeminarId,attendanceId);
//    }
//
//    public List<QuestionInfo> questionInfoList(Long klassSeminarId,Long attendanceId){
//        List<Question> questionList= questionMapper.questionList(klassSeminarId,attendanceId);
//        List<QuestionInfo> questionInfoList=new ArrayList<QuestionInfo>();
//        for(Question question:questionList){
//            Long studentId=question.getStudentId();
//            Long teamId=question.getTeamId();
//            QuestionInfo questionInfo=new QuestionInfo(question);
//            questionInfo.setStudentName(studentMapper.findById(studentId).getStudentName());
//            questionInfo.setTeamSerial(teamMapper.findById(teamId).getTeamSerial());
//            questionInfoList.add(questionInfo);
//        }
//        return questionInfoList;
//    }
//    public int gradeQuestion(Long questionId,double score){
//        return questionMapper.gradeQuestion(questionId,score);
//    }
//    public int updateAttendanceStatus(Long attendanceId,Integer status){
//        return attendanceMapper.updateAttendanceStatus(attendanceId,status);
//    }
//    public int selectQuestion(Long questionId){
//        return questionMapper.selectQuestion(questionId);
//    }
//
//
//    /**
//     * （上传）保存PPT
//     * @param id
//     * @param pptName
//     * @param pptUrl
//     */
//    public void savePPT(Long id,String pptName,String pptUrl){
//        attendanceMapper.updateAttendance(id,pptName,pptUrl);
//    }
//
//    /**
//     * （上传）保存report
//     * @param id
//     * @param pptName
//     * @param pptUrl
//     */
//    public void saveReport(Long id,String pptName,String pptUrl){
//        attendanceMapper.updateAttendance(id,pptName,pptUrl);
//    }
//
//    /**
//     * （下载）获取attendance类->地址文件名
//     * @param id
//     * @return
//     */
//    public Attendance getFile(Long id){
//
//        return attendanceMapper.queryAttendanceById(id);
//    }
//    public List<Question> questions(Long klassSeminarId,Long attendanceId){
//        return questionMapper.questions(klassSeminarId,attendanceId);
//    }
//
//    /**
//     * 删除提问队列
//     * @param klassSeminarId
//     * @param attendanceId
//     * @return
//     */
//    public int deleteQuestionList(Long klassSeminarId,Long attendanceId){
//        return questionMapper.deleteQuestionList(klassSeminarId,attendanceId);
//    }
//}
