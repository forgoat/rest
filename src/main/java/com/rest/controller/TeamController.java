package com.rest.controller;

import com.rest.dao.TeamStudentDao;
import com.rest.dao.TeamValidApplicationDao;
import com.rest.entity.*;
import com.rest.service.*;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
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
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private OrganizeTeamService organizeTeamService;


//    @GetMapping(value = "findStu")
//    public List<KlassStudent> findByTeamId(Long teamId){
//        return teamService.findStuByTeamId(teamId);
//    }
//    @GetMapping(value = "findTeam")
//    public Team findById(Long teamId){
//        return teamService.findTeamByTeamId(teamId);
//    }

    /**
     * 查找小组
     * @param teamId
     * @return
     */
    @GetMapping(value = "{teamId}")
    public ResponseEntity<TeamInfo> teamInfo(@PathVariable("teamId") Long teamId){
       return teamService.teamInfo(teamId);
    }

//    /**
//     * 通过小组认证
//     * @param teamId
//     * @return
//     */
//    @PutMapping(value = "{teamId}/approve")
//    public HttpStatus setValid(@PathVariable("teamId") Long teamId){
//        Team team=teamService.findTeamByTeamId(teamId);
//        if(team==null){
//            return HttpStatus.NOT_FOUND;
//        }
//        else {
//            if (team.getStatus().equals(1)) {
//                return HttpStatus.CONFLICT;
//            } else
//                if (teamService.setValid(teamId) == 1) {
//                    return HttpStatus.OK;
//                }
//                return HttpStatus.FORBIDDEN;
//            }
//        }
//    }

    /**
     *动态修改分组
     * @param teamId
     * @param teamName
     * @param teamSerial
     * @return
     */
    @PutMapping(value = "{teamId}")
    public HttpStatus updateInfo(@PathVariable("teamId") Long teamId,String teamName,Integer teamSerial) {
        if (teamService.updateInfo(teamId,teamName,teamSerial) == 1) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.FORBIDDEN;
        }
    }

    /**
     *删除分组
     * @param teamId
     * @return
     */
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

    /**
     *移除组员
     * @param teamId
     * @param studentId
     * @return
     */
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

    /**
     * 查找学生的teamId
     * @param studentId
     * @return
     */
    @GetMapping(value = "")
    public Long findTeamIdByStudentId(Long studentId,Long courseId)
    {
        return teamService.findTeamByStudentId(studentId,courseId);
    }
//    @GetMapping(value = "findSId")
//    public Long findId(Long courseId){
//        return teamService.findId(courseId);
//    }
    @GetMapping(value = "findConflict")
    public List<Long> findConflict(Long courseId){
        return teamService.findConflictCourse(courseId);
    }

    /**
     * 可分享分组对象
     * @param courseId
     * @return
     */
    @GetMapping(value = "shareObject")
    public List<ShareObject> shareCourseObject(Long courseId) {
        List<Course> courseList = courseService.findAllCourse();
        List<Long> conflictList = teamService.findConflictCourse(courseId);
        List<ShareObject> shareObjectList = new ArrayList<ShareObject>();
        for (Course course : courseList) {
            boolean flag = true;
            for (Long id : conflictList) {
                if (id.equals(course.getId())) {
                    flag = false;
                }
            }
            if (flag) {
                ShareObject shareObject = new ShareObject(course);
                Teacher teacher = teacherService.findById(course.getTeacherId());
                shareObject.setTeacherName(teacher.getTeacherName());
                shareObjectList.add(shareObject);
            }
        }
        return shareObjectList;
    }

    /**
     * 创建小组
     * @param team
     * @return
     */
    @PostMapping(value = "")
    public Long saveTeam(Team team){
        System.out.print("saveTeam");
        return teamService.saveTeam(team);
    }

    /**
     * 增加小组成员
     * @param studentIdList
     * @param teamId
     * @return
     */
    @PostMapping(value = "insertTeammate")
    public HttpStatus insertTeammate(List<Long> studentIdList,Long teamId){
        System.out.println("studentIdList:"+studentIdList+"  teamId:"+teamId);
        HttpStatus httpStatus=(teamService.batchInsertTeamStudent(studentIdList,teamId)==1?HttpStatus.OK:HttpStatus.BAD_REQUEST);
        return httpStatus;
    }

    /**
     * 删除小组成员
     * @param studentId
     * @return
     */
    @PostMapping(value = "deleteByStudentId")
    public HttpStatus deleteByStudentId(Long studentId){
        HttpStatus httpStatus=(teamService.deleteByStudentId(studentId)==1?HttpStatus.OK:HttpStatus.BAD_REQUEST);
        return httpStatus;
    }

    /**
     * 设置小组是否合格
     * @param teamId
     * @return
     */
    @PostMapping(value = "setStatus")
    public HttpStatus setStatus(Long teamId,Long courseId){
        HttpStatus httpStatus=(organizeTeamService.setStatus(teamId,courseId)==1?HttpStatus.OK:HttpStatus.BAD_REQUEST);
        return httpStatus;
    }


     //organizeTeamService.updateTeamTable(team,klassStudent,teamValidApplication);
    /**
     * 判断是否为合法小组
     * @param teamId
     * @param courseId
     * @return
     */
    @PostMapping(value = "isValid")
    public HttpStatus isValid(Long teamId,Long courseId){
        if(organizeTeamService.isValid(teamId,courseId)==true){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }


    @PostMapping(value = "klassTeam")
    public HttpStatus saveKlassTeam(KlassTeam klassTeam){
        if(teamService.saveKlassTeam(klassTeam)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * 查找分组的小组成员
     * @param teamId
     * @return
     */
    @GetMapping(value = "{teamId}/student")
    public List<TeamStudent> findStudentByTeamId(@PathVariable("teamId") Long teamId){
        return teamService.findStudentByTeamId(teamId);
    }
//    @GetMapping(value = "{teamId}/course")
//    public Long findSubTeamKlass(@PathVariable("teamId") Long teamId,Long courseId){
//        return teamService.findSubCourseTeamKlassId(courseId,teamId);
//    }

    /**
     * 删除某课程的班级
     * @param courseId
     * @return
     */
    @DeleteMapping(value = "/course")
    public int deleteTeamByCourseId(Long courseId){
        return teamService.deleteTeamByCourseId(courseId);
    }

    /**
     * 删除班级下的所有队伍副本
     * @param teamId
     * @return
     */
    @DeleteMapping(value = "{teamId}/klassTeam")
    public int deleteKlassTeam(@PathVariable(value = "teamId") Long teamId){
        return teamService.deleteKlassTeam(teamId);
    }

    /**
     * 查找MemberLimitStrategy
     * @param courseId
     * @return
     */
    @PostMapping(value = "queryMemberLimitStrategyById")
    public MemberLimitStrategy queryMemberLimitStrategyById(Long courseId){
        return organizeTeamService.queryMemberLimitStrategyById(organizeTeamService.queryMemberLimitStrategyId(courseId));
    }

    /**
     * 查找CourseMemberLimitStrategy
     * @param courseId
     * @return
     */
    @PostMapping(value = "queryCourseMemberLimitStrategyById")
    public CourseMemberLimitStrategy queryCourseMemberLimitStrategyById(Long courseId){
        return organizeTeamService.queryCourseMemberLimitStrategy(organizeTeamService.queryCourseMemberLimitStrategyId(courseId));
    }

    /**
     * 提交valid申请
     * @param teamValidApplication
     * @return
     */
    @PostMapping(value = "{teamId}/saveTeamValidApplication")
    public int saveTeamValidApplication(@PathVariable(value = "teamId")Long teamId,TeamValidApplication teamValidApplication){
        System.out.println("saveTeamValidApplication");
        return teamService.saveTeamValidApplication(teamValidApplication);
    }


    /**
     * 获取未组队学生
     * @param courseId
     * @return
     */
    @GetMapping(value = "NoTeamStudent")
    public List<Student> findNoTeamStudentByCourseId(Long courseId){
        List<Student> studentList=teamService.findNoTeamStudentByCourseId(courseId);
        return studentList;
    }

    /**
     * 单个添加学生入队
     * @param teamStudent
     * @return
     */
    @PostMapping(value = "saveTeamStudent")
    public HttpStatus saveTeamStudent(TeamStudent teamStudent){
        HttpStatus httpStatus=(teamService.saveTeamStudent(teamStudent)==0)?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return httpStatus;
    }
    @PostMapping(value = "saveAllTeamStudent")
    public HttpStatus saveAllTeamStudent(@RequestBody List<TeamStudent> teamStudentList){
        HttpStatus httpStatus=HttpStatus.OK;
        for (TeamStudent teamStudent:teamStudentList){
            if (teamService.saveTeamStudent(teamStudent)==0){
                httpStatus=HttpStatus.BAD_REQUEST;
            }
        }
        return httpStatus;
    }

    @GetMapping(value = "student")
    public List<TeamStudent> findTeam(Long studentId){
        return teamService.findTeamByStudent(studentId);
    }

    @GetMapping(value = "teamValidApplication")
    public List<TeamValidApplication> findTeamValidApplicationByTeacherId(Long teacherId){
        return teamService.findTeamValidApplicationBy(teacherId);
    }

    @GetMapping(value = "teamValidApplicationInfo")
    public List<TeamValidApplicationInfo> findInfo(Long teacherId){
        return teamService.findTeamValidByTeacherId(teacherId);
    }
}
