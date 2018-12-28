package com.rest.service;

import com.rest.dao.*;
import com.rest.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private SeminarDao seminarDao;
    @Autowired
    private KlassSeminarDao klassSeminarDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeamStudentDao teamStudentDao;
    @Autowired
    private KlassTeamDao klassTeamDao;
    @Autowired
    private KlassDao klassDao;
    public List<Attendance> findAttendanceByKlassSeminarId(Long klassSeminarId){
        return attendanceDao.findAttendanceByKlassSeminarId(klassSeminarId);
    }
    public Attendance findAttendanceByKlassSeminarAndTeamId(Long klassSeminarId,Long teamId){
        return attendanceDao.queryByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
    }
    public Team findTeamByTeamId(Long teamId){
        return teamDao.findById(teamId);
    }
    public List<AttendanceInfo> findAttendanceInfoByKlassSeminarId(Long klassSeminarId){
        List<Attendance> attendanceList=attendanceDao.findAttendanceByKlassSeminarId(klassSeminarId);
        List<AttendanceInfo> attendanceInfoList=new ArrayList<AttendanceInfo>();
        for (Attendance attendance:attendanceList){
            AttendanceInfo attendanceInfo=new AttendanceInfo(attendance);
            Team team=teamDao.findById(attendance.getTeamId());
            attendanceInfo.setTeamName(team.getTeamName());
            attendanceInfo.setTeamSerial(team.getTeamSerial());
            attendanceInfoList.add(attendanceInfo);
        }
        return attendanceInfoList;
    }
    public AttendanceInfomation findAttendanceInformation(Long klassSeminarId){
        List<Attendance> attendanceList=attendanceDao.findAttendanceByKlassSeminarId(klassSeminarId);
        List<AttendanceInfo> attendanceInfoList=new ArrayList<AttendanceInfo>();
        Integer klassSerial=new Integer(0);
        Seminar seminar=new Seminar();
        for (Attendance attendance:attendanceList){
            AttendanceInfo attendanceInfo=new AttendanceInfo(attendance);
            KlassSeminar klassSeminar=klassSeminarDao.findKlassSeminarById(attendance.getKlassSeminarId());
            seminar=seminarDao.findById(klassSeminar.getSeminarId());
            Team team=teamDao.findById(attendance.getTeamId());
            klassSerial=team.getKlassSerial();
            attendanceInfo.setTeamSerial(team.getTeamSerial());
            attendanceInfo.setTeamName(team.getTeamName());
            attendanceInfoList.add(attendanceInfo);
        }
        AttendanceInfomation attendanceInfomation=new AttendanceInfomation(attendanceInfoList);
        attendanceInfomation.setKlassSerial(klassSerial);
        attendanceInfomation.setMaxTeam(seminar.getMaxTeam());
        return attendanceInfomation;
    }
    public int saveAttendance(Attendance attendance){
        return attendanceDao.saveAttendance(attendance);
    }
    public int deleteAttendanceById(Long attendanceId){
        return attendanceDao.deleteById(attendanceId);
    }
    public int saveQuestion(Question question){
        return questionDao.saveQuestion(question);
    }
    public List<Question> questionList(Long klassSeminarId,Long attendanceId){
        return questionDao.questionList(klassSeminarId,attendanceId);
    }

    public List<QuestionInfo> questionInfoList(Long klassSeminarId,Long attendanceId){
        List<Question> questionList=questionDao.questionList(klassSeminarId,attendanceId);
        List<QuestionInfo> questionInfoList=new ArrayList<QuestionInfo>();
        for(Question question:questionList){
            Long studentId=question.getStudentId();
            Long teamId=question.getTeamId();
            QuestionInfo questionInfo=new QuestionInfo(question);
            questionInfo.setStudentName(studentDao.findById(studentId).getStudentName());
            questionInfo.setTeamSerial(teamDao.findById(teamId).getTeamSerial());
            questionInfoList.add(questionInfo);
        }
        return questionInfoList;
    }
    public int gradeQuestion(Long questionId,double score){
        return questionDao.gradeQuestion(questionId,score);
    }
    public int updateAttendanceStatus(Long attendanceId,Integer status){
        return attendanceDao.updateAttendanceStatus(attendanceId,status);
    }
    public int selectQuestion(Long questionId){
        return questionDao.selectQuestion(questionId);
    }

    /**
     *（上传）保存PPT
     * @param pptName
     * @param pptUrl
     * @param studentId
     * @param courseId
     * @return
     */
    public void savePPT(String pptName,String pptUrl,Long studentId,Long courseId,Long seminarId){
        SeminarService seminarService=new SeminarService();
        Long teamId=teamStudentDao.findByStudentId(studentId);
        Long klassSeminarId=seminarService.queryKlassSeminarId(studentId,courseId,seminarId);
        Attendance attendance=attendanceDao.queryByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
        attendance.setPptName(pptName);
        attendance.setPptUrl(pptUrl);
        attendanceDao.saveAttendance(attendance);
    }

    /**
     *（上传）保存report
     * @param reportName
     * @param reportUrl
     * @param studentId
     * @param courseId
     * @param seminarId
     */
    public void saveReport(String reportName,String reportUrl,Long studentId,Long courseId,Long seminarId){
        SeminarService seminarService=new SeminarService();
        Long teamId=teamStudentDao.findByStudentId(studentId);
        Long klassSeminarId=seminarService.queryKlassSeminarId(studentId,courseId,seminarId);
        Attendance attendance=attendanceDao.queryByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
        attendance.setReportName(reportName);
        attendance.setReportUrl(reportUrl);
        attendanceDao.saveAttendance(attendance);
    }

    /**
     * （下载）获取attendance类->地址文件名
     * @param studentId
     * @param courseId
     * @param seminarId
     * @return
     */
    public Attendance getFile(Long studentId,Long courseId,Long seminarId){
        SeminarService seminarService=new SeminarService();
        Long teamId=teamStudentDao.findByStudentId(studentId);
        Long klassSeminarId=seminarService.queryKlassSeminarId(studentId,courseId,seminarId);
        Attendance attendance=attendanceDao.queryByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
        return attendance;
    }



}
