package com.rest.service;

import com.rest.dao.*;
import com.rest.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private KlassStudentDao klassStudentDao;
    @Autowired
    private TeamStudentDao teamStudentDao;
    @Autowired
    private KlassTeamDao klassTeamDao;
    @Autowired
    private ConflictCourseStrategyDao conflictCourseStrategyDao;
    public Long findId(Long courseId){
        return conflictCourseStrategyDao.findId(courseId);
    }
    public List<KlassStudent> findStuByTeamId(Long teamId){
        return klassStudentDao.findByTeamId(teamId);
    }
    public Team findTeamByTeamId(Long teamId){
        return teamDao.findById(teamId);
    }
    public int setValid(Long teamId){
        return teamDao.setValid(teamId);
    }
    public int updateInfo(Long teamId,String team_name,Integer team_serial){
        return teamDao.updateInfo(teamId,team_name,team_serial);
    }
    public int deleteTeam(Long teamId){
        return teamDao.deleteTeam(teamId);
    }
    public KlassStudent findByStudentId(Long studentId){
        return klassStudentDao.findByStudentId(studentId);
    }
    public int quitTeam(Long studentId){
        return klassStudentDao.quitTeam(studentId);
    }
    public int updateTeam(Long studentId,Long teamId)
    {
        return klassStudentDao.updateTeam(studentId,teamId);
    }
    public Long findTeamByStudentId(Long studentId){
        return teamStudentDao.findByStudentId(studentId);
    }
    public List<Long> findAllKlass(Long teamId){
        return klassTeamDao.findByTeamId(teamId);
    }
    public List<Long> findConflict(Long id){
        return conflictCourseStrategyDao.findConflict(id);
    }
    public List<Long> findConflictCourse(Long courseId){
        Long id=conflictCourseStrategyDao.findId(courseId);
        List<Long> conflict=conflictCourseStrategyDao.findConflict(id);
        return conflict;
    }
}
