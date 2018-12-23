package com.rest.controller;

import com.rest.entity.Course;
import com.rest.entity.Klass;
import com.rest.entity.ShareSeminarApplication;
import com.rest.entity.ShareTeamApplication;
import com.rest.service.CourseService;
import com.rest.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private KlassService klassService;
    @GetMapping(value = "")
    public ResponseEntity<List<Course>> findAllCourse(){
        List<Course> courseList=courseService.findAllCourse();
        HttpStatus httpStatus=(courseList.isEmpty())?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity<List<Course>>(courseList,httpStatus);
    }
    @PostMapping(value = "")
    public ResponseEntity<Long> saveCourse(Course course){
        if(courseService.saveCourse(course)==1){
            return new ResponseEntity<Long>(course.getId(),HttpStatus.ACCEPTED);
        }
        else {
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "{courseId}")
    public ResponseEntity<Course> findById(@PathVariable("courseId") Long courseId){
        Course course=courseService.findById(courseId);
        HttpStatus httpStatus=(course!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        ResponseEntity<Course> courseResponseEntity=new ResponseEntity<Course>(course,httpStatus);
        System.out.println(courseResponseEntity.getStatusCode());
        return courseResponseEntity;
    }
    @DeleteMapping(value = "{courseId}")
    public HttpStatus deleteById(@PathVariable("courseId")Long courseId){
        if(courseService.deleteById(courseId)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
    @GetMapping(value = "{courseId}/class")
    public ResponseEntity<List<Klass>> findClass(@PathVariable("courseId") Long courseId){
        List<Klass> klassList=klassService.findByCourseId(courseId);
        HttpStatus httpStatus=(klassList.isEmpty())?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity<List<Klass>>(klassList,httpStatus);
    }
    @PostMapping(value = "{courseId}/seminarsharerequest")
    public ResponseEntity<Long> sendSeminarShare(@PathVariable("courseId") Long mainCourseId,Long subCourseId){
        Course mainCourse=courseService.findById(mainCourseId);
        if(mainCourse==null){
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.NOT_FOUND);
        }
        else {
            Course subCourse = courseService.findById(subCourseId);
            Long subCourseTeacherId = subCourse.getTeacher_id();
            ShareSeminarApplication shareSeminarApplication = new ShareSeminarApplication();
            shareSeminarApplication.setMain_course_id(mainCourseId);
            shareSeminarApplication.setSub_course_id(subCourseId);
            shareSeminarApplication.setSub_course_teacher_id(subCourseTeacherId);
            if (courseService.sendSeminarShare(shareSeminarApplication) == 1) {
                return new ResponseEntity<Long>(shareSeminarApplication.getId(), HttpStatus.OK);
            } else {
                Long id = new Long(0);
                return new ResponseEntity<Long>(id, HttpStatus.BAD_REQUEST);
            }
        }
    }
    @PostMapping(value = "{courseId}/teamsharerequest")
    public ResponseEntity<Long> sendTeamShare(Long mainCourseId,Long subCourseId){
        Course mainCourse=courseService.findById(mainCourseId);
        if(mainCourse==null){
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.NOT_FOUND);
        }
        else {
            Course subCourse = courseService.findById(subCourseId);
            Long subCourseTeacherId = subCourse.getTeacher_id();
            ShareTeamApplication shareTeamApplication = new ShareTeamApplication();
            shareTeamApplication.setMainCourseId(mainCourseId);
            shareTeamApplication.setSubCourseId(subCourseId);
            shareTeamApplication.setSubCourseTeacherId(subCourseTeacherId);
            if (courseService.sendTeamShare(shareTeamApplication) == 1) {
                return new ResponseEntity<Long>(shareTeamApplication.getId(), HttpStatus.OK);
            } else {
                Long id = new Long(0);
                return new ResponseEntity<Long>(id, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
