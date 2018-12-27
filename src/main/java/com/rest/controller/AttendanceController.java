package com.rest.controller;

import com.rest.entity.*;
import com.rest.service.AttendanceService;
import com.rest.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private ScoreService scoreService;

    /**
     * 查看报名
     * @param klassSeminarId
     * @return
     */
    @GetMapping(value = "{klassSeminarId}")
    public List<Attendance> findAttendanceByKlassSeminarId(@PathVariable("klassSeminarId") Long klassSeminarId){
        return attendanceService.findAttendanceByKlassSeminarId(klassSeminarId);
    }

    /**
     * 查看报名
     * @param klassSeminarId
     * @return
     */
    @GetMapping(value = "{klassSeminarId}/info")
    public List<AttendanceInfo> findAttendanceInfoByKlassSeminarId(@PathVariable("klassSeminarId") Long klassSeminarId){
        return attendanceService.findAttendanceInfoByKlassSeminarId(klassSeminarId);
    }

    /**
     * 查看报名信息
     * @param klassSeminarId
     * @return
     */
    @GetMapping(value = "{klassSeminarId}/infomation")
    public AttendanceInfomation findAttendanceInformationByKlassSeminarId(@PathVariable("klassSeminarId") Long klassSeminarId){
        return attendanceService.findAttendanceInformation(klassSeminarId);
    }

    /**
     * 保存报名
     * @param attendance
     * @return
     */
    @PostMapping(value = "")
    public HttpStatus saveAttendance(Attendance attendance){
        HttpStatus httpStatus=(attendanceService.saveAttendance(attendance)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }

    /**
     * 取消报名
     * @param id
     * @return
     */
    @DeleteMapping(value = "{attendanceId}")
    public HttpStatus deleteAttendanceById(@PathVariable("attendanceId") Long id){
        HttpStatus httpStatus=(attendanceService.deleteAttendanceById(id)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }

    /**
     *保存提问
     * @param question
     * @return
     */
    @PostMapping(value = "question")
    public HttpStatus saveQuestion(Question question){
        HttpStatus httpStatus=(attendanceService.saveQuestion(question)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }

    /**
     * 提问打分
     * @param questionId
     * @param score
     * @return
     */
    @PutMapping(value = "question/{questionId}")
    public HttpStatus gradeQuestion(@PathVariable("questionId") Long questionId,double score){
        HttpStatus httpStatus=(attendanceService.gradeQuestion(questionId,score)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }

    /**
     * 根据id查看提问
     * @param id
     * @return
     */
    @GetMapping(value = "question/{questionId}")
    public Question findQuestionById(@PathVariable("questionId") Long id){
        return scoreService.findQuestionById(id);
    }

    @GetMapping(value = "{attendanceId}/questionList")
    public List<QuestionInfo> questionList(Long klassSeminarId,@PathVariable("attendanceId") Long attendanceId){
        return attendanceService.questionInfoList(klassSeminarId,attendanceId);
    }

}
