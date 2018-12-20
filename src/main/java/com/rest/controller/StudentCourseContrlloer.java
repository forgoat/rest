package com.rest.controller;

import com.rest.dao.CourseDao;
import com.rest.dao.TeamDao;
import com.rest.entity.Course;
import com.rest.entity.Score;
import com.rest.entity.Student;
import com.rest.entity.Team;
import com.rest.service.CourseService;
import com.rest.service.ScoreService;
import com.rest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "course")
public class StudentCourseContrlloer {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private TeamService teamService;

    //5-3
    @GetMapping(value = "queryCourseByStudentId")
    public List<Course> queryCourseByStudentId(BigInteger student_id){
        return courseService.queryCourseByStudentId(student_id);
    }

    //5-3-1
    @RequestMapping(value = "score", method = RequestMethod.GET)
    public List<Score> queryScore(BigInteger course_id,
                                  BigInteger student_id,
                                  BigInteger round_id,
                                  BigInteger seminar_id){
    //public String courseScore(@PathVariable("courseId") BigInteger course_id,BigInteger student_id,BigInteger round_id,BigInteger seminar_id) {
        return scoreService.queryScore(course_id,student_id,round_id,seminar_id);
        //return "【ECHO】" + course_id;
    }



  //5-3-2
    // 5-3-2-1
  @RequestMapping(value = "team", method = RequestMethod.GET)
   public List<Team> queryAllTeam(BigInteger course_id){
       return teamService.queryAllTeam(course_id);
   }

   @RequestMapping(value = "noTeam", method = RequestMethod.GET)
   public  List<Student> queryStudentNoTeam( BigInteger course_id){
       return teamService.queryStudentNoTeam();
    }

//    //5-3-2-3
//    @GetMapping(value = "course/team")
//    public int createTeamWithUpdate(Team team,
//                                    BigInteger team_id,
//                                    BigInteger teacher_id,
//                                    BigInteger klass_id,
//                                    BigInteger student_id,
//                                    BigInteger course_id){
//        return teamService.createTeamWithUpdate(team,
//                 team_id,
//                 teacher_id,
//                 klass_id,
//                 student_id,
//                 course_id
//                );
//    }





}
