package com.rest.controller;

import com.rest.entity.KlassStudent;
import com.rest.entity.Student;
import com.rest.entity.Team;
import com.rest.entity.TeamInfo;
import com.rest.service.StudentService;
import com.rest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rest.entity.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private StudentService studentService;
    @GetMapping(value = "findStu")
    public List<KlassStudent> findByTeamId(Long teamId){
        return teamService.findStuByTeamId(teamId);
    }
    @GetMapping(value = "findTeam")
    public Team findById(Long teamId){
        return teamService.findTeamByTeamId(teamId);
    }
    @GetMapping(value = "{teamId}")
    public ResponseEntity<TeamInfo> teamInfo(@PathVariable("teamId") Long teamId){
        TeamInfo teamInfo=new TeamInfo();
        Team team=teamService.findTeamByTeamId(teamId);
        teamInfo.setTeamId(team.getId());
        teamInfo.setClassId(team.getKlass_id());
        teamInfo.setCourseId(team.getCourse_id());
        teamInfo.setTeam_name(team.getTeam_name());
        teamInfo.setTeam_serial(team.getTeam_serial());
        Student leader=studentService.findById(team.getLeader_id());
        teamInfo.setLeader(leader);
        List<KlassStudent> klassStudentList=teamService.findStuByTeamId(teamId);
        List<Student> member=new ArrayList<Student>();
        for(KlassStudent klassStudent:klassStudentList){
            if(klassStudent.getStudent_id().equals(team.getLeader_id())){
            }
            else {
                Student student = studentService.findById(klassStudent.getStudent_id());
                member.add(student);
            }
        }
        teamInfo.setMember(member);
        return new ResponseEntity<TeamInfo>(teamInfo, HttpStatus.OK);
    }
    @PutMapping(value = "{teamId}/approve")
    public HttpStatus setValid(@PathVariable("teamId") Long teamId){
        Team team=teamService.findTeamByTeamId(teamId);
        if(team==null){
            return HttpStatus.NOT_FOUND;
        }
        else {
            if (team.getStatus().equals(1)) {
                return HttpStatus.CONFLICT;
            } else {
                if (teamService.setValid(teamId) == 1) {
                    return HttpStatus.OK;
                }
                return HttpStatus.FORBIDDEN;
            }
        }
    }
    public HttpStatus updateInfo(Long teamId,String team_name,Integer team_serial){
        
    }
}
