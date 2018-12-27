package com.rest.service;

import com.rest.dao.AttendanceDao;
import com.rest.dao.KlassSeminarDao;
import com.rest.dao.SeminarDao;
import com.rest.dao.TeamDao;
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
    public List<Attendance> findAttendanceByKlassSeminarId(Long klassSeminarId){
        return attendanceDao.findAttendanceByKlassSeminarId(klassSeminarId);
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
}
