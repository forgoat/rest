package com.rest.service;

import com.rest.dao.*;
import com.rest.entity.*;
import org.apache.poi.ss.formula.functions.T;
import com.rest.dao.KlassStudentDao;
import com.rest.dao.KlassTeamDao;
import com.rest.dao.TeamDao;
import com.rest.dao.TeamStudentDao;
import com.rest.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
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

    public List<Team> queryAllTeam(Long courseId){
        return teamDao.queryAllTeam(courseId);
    }

    public List<Student> queryStudentNoTeam(){
        return teamDao.queryStudentNoTeam();
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
        if (teamDao.deleteTeam(teamId)==1){
            teamStudentDao.deleteByTeamId(teamId);
            klassTeamDao.deleteKlassTeamsByTeamId(teamId);
            return 1;
        }
        else {
            return 0;
        }
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
    public List<Team> findTeamByCourseId(Long courseId){
        return teamDao.findByCourseId(courseId);
    }
    public int deleteTeamStudentByTeamId(Long teamId){
        return teamStudentDao.deleteByTeamId(teamId);
    }
    public int deleteKlassTeamByTeamId(Long teamId){
        return klassTeamDao.deleteKlassTeamsByTeamId(teamId);
    }
    public int saveTeam(Team team){
        Integer teamSerial=new Integer(0);
        List<Team> teamList=teamDao.findByCourseId(team.getCourseId());
        if(!teamList.isEmpty()){
            for(Team team1:teamList){
                if(team1.getTeamSerial()>teamSerial){
                    teamSerial=team1.getTeamSerial();
                }
            }
        }
        team.setTeamSerial(teamSerial+1);
        if(teamDao.save(team)==1){
            KlassTeam klassTeam=new KlassTeam();
            klassTeam.setKlassId(team.getKlassId());
            klassTeam.setTeamId(team.getId());
            klassTeamDao.save(klassTeam);
            return 1;
        }
        else {
            return 0;
        }
    }
    public int saveKlassTeam(KlassTeam klassTeam){
        return klassTeamDao.save(klassTeam);
    }
    public List<TeamStudent> findStudentByTeamId(Long teamId){
        return teamStudentDao.findByTeamId(teamId);
    }
    public Long findKlassId(Long teamId,Long courseId){
        List<TeamStudent> teamStudentList=teamStudentDao.findByTeamId(teamId);
        List<Integer> num=new ArrayList<Integer>();
        List<Long> klassIdList=new ArrayList<Long>();
        Long classId=new Long(0);
        for(TeamStudent teamStudent:teamStudentList){
            Long klassId=klassStudentDao.findKlass(teamStudent.getStudentId(),courseId);
            boolean flag=true;
            int i=0;
            for(i=0;i<klassIdList.size();i++){
                flag=true;
                if(klassId.equals(klassIdList.get(i))){
                    flag=false;
                    break;
                }
            }
            if (flag){
                num.add(new Integer(1));
            }
            else {
                num.set(i,num.get(i)+1);
            }
        }
        Integer max=num.get(0);
        Integer maxI=0;
        boolean same=true;
        for(int i=1;i<num.size();i++){
            if(!num.get(i).equals(max)){
                same=false;
                if (num.get(i)>max){
                    max=num.get(i);
                    maxI=i;
                }
            }
        }
        if(!same) {
            classId = klassIdList.get(maxI);
        }
        else {
            Integer minI=new Integer(1000);
            for(Long klassId:klassIdList){
                List<KlassTeam> klassTeamList=klassTeamDao.findByKlassId(klassId);
                if(minI>klassTeamList.size()){
                    classId=klassId;
                }
            }
        }
        return classId;
    }
    public int deleteTeamByCourseId(Long courseId){
        List<Team> teamList=teamDao.findByCourseId(courseId);
        if(!teamList.isEmpty()) {
            for(Team team:teamList){
                klassTeamDao.deleteKlassTeamsByTeamId(team.getId());
            }
            return teamDao.deleteTeamByCourseId(courseId);
        }
        else {
            return 1;
        }
    }
    public int deleteKlassTeam(Long teamId){
        return klassTeamDao.deleteKlassTeamsByTeamId(teamId);
    }
}
