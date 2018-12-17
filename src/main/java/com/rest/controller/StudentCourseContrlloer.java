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
@RequestMapping("/course")
public class StudentCourseContrlloer {
    @Autowired
    private CourseService courseService;
    private ScoreService scoreService;
    private TeamService teamService;

    //5-3
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Course> queryCourseByStudentId(BigInteger student_id){
        return courseService.queryCourseByStudentId(student_id);
    }

    //5-3-1
    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public List<Score> queryScore(BigInteger course_id,BigInteger student_id,BigInteger seminar_id,BigInteger round_id){
    //public List<Score> queryScore(@PathVariable("courseId") BigInteger course_id,BigInteger student_id,BigInteger seminar_id,BigInteger round_id) {

        return scoreService.queryScore(course_id,student_id,round_id,seminar_id);
    }



  //5-3-2
    // 5-3-2-1
  @RequestMapping(value = "/{courseId}/team", method = RequestMethod.GET)
   public List<Team> queryAllTeam(@PathVariable("courseId") BigInteger course_id){
       return teamService.queryAllTeam(course_id);
   }

   @RequestMapping(value = "/{courseId}/noTeam", method = RequestMethod.GET)
   public  List<Student> queryStudentNoTeam(@PathVariable("courseId") BigInteger course_id){
       return teamService.queryStudentNoTeam();
    }

    //5-3-2-3
//    @GetMapping(value = "/course/{courseId}/team")
//    public int createTeamWithUpdate(Team team,
//                                    BigInteger team_id,
//                                    BigInteger teacher_id,
//                                    BigInteger status,
//                                    BigInteger klass_id,
//                                    BigInteger student_id,
//                                    BigInteger course_id){
//        return teamService.createTeamWithUpdate(team,
//                 team_id,
//                 teacher_id,
//                 status,
//                 klass_id,
//                 student_id,
//                 course_id
//                );
//    }
//
//    //
//


}
