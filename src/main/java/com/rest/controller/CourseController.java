package com.rest.controller;

import com.rest.entity.*;
import com.rest.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    @Autowired
    private ImportExcelService importExcelService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private RoundService roundService;
    @Autowired
    private SeminarService seminarService;


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
     * 创建班级 导入学生名单
     * @param courseId
     * @Param klass
     * @Param myFile
     * @return
     */
    @PostMapping(value = "{courseId}/class")
    public ResponseEntity<Long> saveKlass(@PathVariable("courseId")Long courseId,
                                          Klass klass,
                                          @RequestParam("myFile") MultipartFile myFile){

        if(klassService.saveKlass(klass)==1){
            //  Excel导入数据到数据库
            Integer nums = importExcelService.importExcel(myFile);

            return new ResponseEntity<Long>(klass.getId(),HttpStatus.ACCEPTED);
        }
        else {
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.FORBIDDEN);
        }
    }



    /**
     * 查找老师的课程
     * @param teacherId
     * @return
     */
    @GetMapping(value = "/teacher/{teacherId}")
    public ResponseEntity<List<Course>> findByTeacherId(@PathVariable("teacherId") Long teacherId){
        List<Course> courseList=courseService.findByTeacherId(teacherId);
        HttpStatus httpStatus=(courseList.isEmpty())?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return  new ResponseEntity<List<Course>>(courseList,httpStatus);
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

    /**
     * 查看讨论课共享信息申请
     * @param courseId
     * @return
     */
    @GetMapping(value = "{courseId}/seminarsharerequest")
    public ResponseEntity<List<ShareSeminarApplication>> findSeminarShare(@PathVariable("courseId") Long courseId){
        List<ShareSeminarApplication> shareSeminarApplicationList=courseService.findSeminarShare(courseId);
        HttpStatus httpStatus=(shareSeminarApplicationList.isEmpty())?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity<List<ShareSeminarApplication>>(shareSeminarApplicationList,httpStatus);
    }

    /**
     * 同意共享讨论课
     * @param shareSeminarId
     * @return
     */
    @PutMapping(value = "seminarsharerequest")
    public HttpStatus acceptSeminarShare(Long shareSeminarId){
        if(courseService.acceptSeminarShare(shareSeminarId)==1){
            ShareSeminarApplication shareSeminarApplication=courseService.findByShareSeminarId(shareSeminarId);
            Long subCourseId=shareSeminarApplication.getSubCourseId();
            Long mainCourseId=shareSeminarApplication.getMainCourseId();
            List<Seminar> seminarList=seminarService.findByCourseId(subCourseId);
            for(Seminar seminar:seminarList){
                Long roundId=seminar.getRoundId();
                if(seminarService.deleteSeminar(seminar.getId())==1){
                    seminarService.deleteBySeminarId(seminar.getId());
                    List<Seminar> seminars=seminarService.findByRoundId(roundId);
                    if(seminars.isEmpty()){
                        roundService.deleteRound(roundId);
                    }
                }
            }

            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * 拒绝共享或取消共享
     * @param shareSeminarId
     * @return
     */
    @PutMapping(value = "/seminarshare")
    public HttpStatus rejectSeminarShare(Long shareSeminarId){
        ShareSeminarApplication shareSeminarApplication=courseService.findByShareSeminarId(shareSeminarId);
        Long subCourseId=shareSeminarApplication.getSubCourseId();
        if(courseService.rejectSeminarShare(shareSeminarId)==1){
            if (shareSeminarApplication.getStatus().equals(1)){
               List<Seminar> seminarList=seminarService.findByCourseId(subCourseId);
               for(Seminar seminar:seminarList){
                   Long roundId=seminar.getRoundId();
                   if(seminarService.deleteSeminar(seminar.getId())==1){
                       seminarService.deleteBySeminarId(seminar.getId());
                       List<Seminar> seminars=seminarService.findByRoundId(roundId);
                       if(seminars.isEmpty()){
                           roundService.deleteRound(roundId);
                       }
                   }
               }
            }
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    /**
     *
     * @param shareSeminarId
     * @return
     */
//    @PutMapping(value = "{courseId}/seminarshare/{shareSeminarId}")
//    public HttpStatus acceptSeminarShare(@PathVariable("shareSeminarId") Long shareSeminarId) {
//        HttpStatus httpStatus = HttpStatus.OK;
//        return httpStatus;
//    }

//    /**
//     * 查找该课程下的学生所属队伍
//     * @param courseId
//     * @param studentId
//     * @return
//     */
//    @GetMapping(value = "{courseId}/team")
//    public ResponseEntity<Long> findTeam(@PathVariable("courseId") Long courseId,Long studentId){
//        Long teamId=klassService.findTeam(courseId,studentId);
//        HttpStatus httpStatus=(teamId!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
//        return new ResponseEntity<Long>(teamId,httpStatus);
//    }

    /**
     * 查找学生的班级讨论课
     * @param courseId
     * @param studentId
     * @return
     */
    @GetMapping(value = "{courseId}/seminar")
    public List<SeminarInfo> findKlassTeam(@PathVariable("courseId") Long courseId,Long studentId){
        List<Klass> classlist=klassService.findByCourseId(courseId);
        Long teamId=teamService.findTeamByStudentId(studentId);
        System.out.println("teamId is "+teamId);
        List<Long> klassId=teamService.findAllKlass(teamId);
        Long classId=new Long(0);
        List<Round> roundList=roundService.findByCourseId(courseId);
        for(Klass klass:classlist){
            for(Long k:klassId){
                if(klass.getId().equals(k)){
                    classId=k;
                }
            }
        }
        System.out.println("classId is "+classId);
        List<SeminarInfo> seminarInfoList=new ArrayList<SeminarInfo>();
        for(Round round:roundList){
            System.out.println("roundId is "+round.getId());
            SeminarInfo seminarInfo=new SeminarInfo(round);
            seminarInfo.setKlassId(classId);
            seminarInfo.setEnrollNumber(roundService.findEnrollNumber(round.getId(),classId));
            List<Seminar> seminarList=seminarService.findByRoundId(round.getId());
            List<KlassSeminarInfo> klassSeminarInfoList=new ArrayList<KlassSeminarInfo>();
            for(Seminar seminar:seminarList){
                System.out.println("name is "+seminar.getSeminarName());
                KlassSeminar klassSeminar=seminarService.findKlassSeminar(classId,seminar.getId());
                KlassSeminarInfo klassSeminarInfo=new KlassSeminarInfo(klassSeminar);
                klassSeminarInfo.setSeminarName(seminar.getSeminarName());
                klassSeminarInfoList.add(klassSeminarInfo);
            }
            seminarInfo.setKlassSeminarInfoList(klassSeminarInfoList);
            seminarInfoList.add(seminarInfo);
        }
        return seminarInfoList;
    }

}
