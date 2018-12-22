package com.rest.service;

import com.rest.dao.KlassStudentDao;
import com.rest.dao.TeamDao;
import com.rest.entity.KlassStudent;
import com.rest.entity.Student;
import com.rest.entity.Team;
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
    public List<Team> queryAllTeam(BigInteger course_id){
        return teamDao.queryAllTeam(course_id);
    }

    public List<Student> queryStudentNoTeam(){
        return teamDao.queryStudentNoTeam();
    }

    public int createTeamWithUpdate(Team team,
                                    BigInteger team_id,
                                    BigInteger teacher_id,
                                    BigInteger klass_id,
                                    BigInteger student_id,
                                    BigInteger course_id)
    {
        int count=teamDao.createTeam(team);
        teamDao.updateKlassStudent( team_id,klass_id,student_id,course_id);
        teamDao.updateTeamValidApplication(team_id, teacher_id);

        return count;
    }
    public List<KlassStudent> findStuByTeamId(Long teamId){
        return klassStudentDao.findByTeam_id(teamId);
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

}
