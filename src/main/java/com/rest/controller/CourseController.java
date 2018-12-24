package com.rest.controller;

import com.rest.entity.*;
import com.rest.service.CourseService;
import com.rest.service.KlassService;
import com.rest.service.SeminarService;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private KlassService klassService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TeacherService teacherService;

    /**
     * 查找所有课程
     * @return
     */
    @GetMapping(value = "")
    public ResponseEntity<List<Course>> findAllCourse(){
        List<Course> courseList=courseService.findAllCourse();
        HttpStatus httpStatus=(courseList.isEmpty())?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity<List<Course>>(courseList,httpStatus);
    }

    /**
     * 新建课程
     * @param course
     * @return
     */
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

    /**
     * 查找课程
     * @param courseId
     * @return
     */
    @GetMapping(value = "{courseId}")
    public ResponseEntity<Course> findById(@PathVariable("courseId") Long courseId){
        Course course=courseService.findById(courseId);
        HttpStatus httpStatus=(course!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        ResponseEntity<Course> courseResponseEntity=new ResponseEntity<Course>(course,httpStatus);
        System.out.println(courseResponseEntity.getStatusCode());
        return courseResponseEntity;
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping(value = "{courseId}")
    public HttpStatus deleteById(@PathVariable("courseId")Long courseId){
        if(courseService.deleteById(courseId)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * 获取班级
     * @param courseId
     * @return
     */
    @GetMapping(value = "{courseId}/class")
    public ResponseEntity<List<Klass>> findClass(@PathVariable("courseId") Long courseId){
        List<Klass> klassList=klassService.findByCourseId(courseId);
        HttpStatus httpStatus=(klassList.isEmpty())?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity<List<Klass>>(klassList,httpStatus);
    }

    /**
     *发出讨论课共享申请
     * @param mainCourseId
     * @param subCourseId
     * @return
     */
    @PostMapping(value = "{courseId}/seminarsharerequest")
    public ResponseEntity<Long> sendSeminarShare(@PathVariable("courseId") Long mainCourseId,Long subCourseId){
        Course mainCourse=courseService.findById(mainCourseId);
        if(mainCourse==null){
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.NOT_FOUND);
        }
        else {
            Course subCourse = courseService.findById(subCourseId);
            Long subCourseTeacherId = subCourse.getTeacherId();
            ShareSeminarApplication shareSeminarApplication = new ShareSeminarApplication();
            shareSeminarApplication.setMainCourseId(mainCourseId);
            shareSeminarApplication.setSubCourseId(subCourseId);
            shareSeminarApplication.setSubCourseTeacherId(subCourseTeacherId);
            if (courseService.sendSeminarShare(shareSeminarApplication) == 1) {
                SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
                simpleMailMessage.setTo(teacherService.findById(subCourseTeacherId).getEmail());
                simpleMailMessage.setFrom("1010410164@qq.com");
                simpleMailMessage.setSubject("共享讨论课邀请");
                simpleMailMessage.setText(mainCourse.getCourseName());
                javaMailSender.send(simpleMailMessage);
                return new ResponseEntity<Long>(shareSeminarApplication.getId(), HttpStatus.OK);
            } else {
                Long id = new Long(0);
                return new ResponseEntity<Long>(id, HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * 共享分组申请
     * @param mainCourseId
     * @param subCourseId
     * @return
     */
    @PostMapping(value = "{courseId}/teamsharerequest")
    public ResponseEntity<Long> sendTeamShare(@PathVariable("courseId") Long mainCourseId,Long subCourseId){
        Course mainCourse=courseService.findById(mainCourseId);
        if(mainCourse==null){
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.NOT_FOUND);
        }
        else {
            Course subCourse = courseService.findById(subCourseId);
            Long subCourseTeacherId = subCourse.getTeacherId();
            ShareTeamApplication shareTeamApplication = new ShareTeamApplication();
            shareTeamApplication.setMainCourseId(mainCourseId);
            shareTeamApplication.setSubCourseId(subCourseId);
            shareTeamApplication.setSubCourseTeacherId(subCourseTeacherId);
            if (courseService.sendTeamShare(shareTeamApplication) == 1) {
                SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
                simpleMailMessage.setTo(teacherService.findById(subCourseTeacherId).getEmail());
                simpleMailMessage.setFrom("1010410164@qq.com");
                simpleMailMessage.setSubject("共享分组邀请");
                simpleMailMessage.setText(mainCourse.getCourseName());
                javaMailSender.send(simpleMailMessage);
                return new ResponseEntity<Long>(shareTeamApplication.getId(), HttpStatus.OK);
            } else {
                Long id = new Long(0);
                return new ResponseEntity<Long>(id, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
