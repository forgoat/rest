package com.rest.controller;

import com.rest.entity.*;
import com.rest.service.ScoreService;
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
    @Autowired
    private ScoreService scoreService;
//    @GetMapping(value = "findStu")
//    public List<KlassStudent> findByTeamId(Long teamId){
//        return teamService.findStuByTeamId(teamId);
//    }
//    @GetMapping(value = "findTeam")
//    public Team findById(Long teamId){
//        return teamService.findTeamByTeamId(teamId);
//    }
    @GetMapping(value = "{teamId}")
    public ResponseEntity<TeamInfo> teamInfo(@PathVariable("teamId") Long teamId){
        TeamInfo teamInfo=new TeamInfo();
        Team team=teamService.findTeamByTeamId(teamId);
        teamInfo.setTeamId(team.getId());
        teamInfo.setClassId(team.getKlassId());
        teamInfo.setCourseId(team.getCourseId());
        teamInfo.setTeamName(team.getTeamName());
        teamInfo.setTeamSerial(team.getTeamSerial());
        Student leader=studentService.findById(team.getLeaderId());
        teamInfo.setLeader(leader);
        List<KlassStudent> klassStudentList=teamService.findStuByTeamId(teamId);
        List<Student> member=new ArrayList<Student>();
        for(KlassStudent klassStudent:klassStudentList){
            if(klassStudent.getStudentId().equals(team.getLeaderId())){
            }
            else {
                Student student = studentService.findById(klassStudent.getStudentId());
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
    @PutMapping(value = "{teamId}")
    public HttpStatus updateInfo(@PathVariable("teamId") Long teamId,String team_name,Integer team_serial) {
        if (teamService.updateInfo(teamId, team_name, team_serial) == 1) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.FORBIDDEN;
        }
    }
    @DeleteMapping(value = "{teamId}")
    public HttpStatus deleteTeam(@PathVariable("teamId") Long teamId){
        Team team=teamService.findTeamByTeamId(teamId);
        if (team==null){
            return HttpStatus.NOT_FOUND;
        }
        else {
            if(teamService.deleteTeam(teamId)==1){
                return HttpStatus.OK;
            }
            else {
                return HttpStatus.FORBIDDEN;
            }
        }
    }
    @PutMapping(value = "{teamId}/remove")
    public HttpStatus removeMember(@PathVariable("teamId") Long teamId,Long studentId){
        Team team=teamService.findTeamByTeamId(teamId);
        Student student=studentService.findById(studentId);
        if (team==null){
            return HttpStatus.NOT_FOUND;
        }
        if (student==null){
            return HttpStatus.BAD_REQUEST;
        }
        KlassStudent klassStudent=teamService.findByStudentId(studentId);
        if (klassStudent.getTeamId().equals(teamId)){
            if(teamService.quitTeam(studentId)==1){
                return HttpStatus.OK;
            }
        }
        return HttpStatus.CONFLICT;
    }
    @PutMapping(value = "{teamId}/add")
    public HttpStatus addMember(@PathVariable("teamId") Long teamId,Long studentId){
        Team team=teamService.findTeamByTeamId(teamId);
        Student student=studentService.findById(studentId);
        if(team==null){
            return HttpStatus.NOT_FOUND;
        }
        if (student==null){
            return HttpStatus.BAD_REQUEST;
        }
        KlassStudent klassStudent=teamService.findByStudentId(studentId);
        if (klassStudent.getTeamId().equals(teamId)){
            return HttpStatus.CONFLICT;
        }
        else {
            if (teamService.updateTeam(studentId,teamId)==1){
                return HttpStatus.OK;
            }
        }
        return HttpStatus.FORBIDDEN;
    }
//    @GetMapping(value = "{teamId}/seminar/{seminarId}/seminarscore",produces = "application/json;charset=UTF-8")
//    public ResponseEntity<SeminarScore> seeScore(@PathVariable("teamId") Long teamId,@PathVariable("seminarId") Long klassSeminarId){
//        SeminarScore seminarScore=scoreService.findByTeamIdAndSeminarId(teamId,klassSeminarId);
//        HttpStatus httpStatus=(seminarScore!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
//        return new ResponseEntity<SeminarScore>(seminarScore,httpStatus);
//    }
}
