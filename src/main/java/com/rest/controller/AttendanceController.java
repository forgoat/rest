package com.rest.controller;

import com.rest.entity.Attendance;
import com.rest.entity.AttendanceInfo;
import com.rest.entity.AttendanceInfomation;
import com.rest.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @GetMapping(value = "{klassSeminarId}")
    public List<Attendance> findAttendanceByKlassSeminarId(@PathVariable("klassSeminarId") Long klassSeminarId){
        return attendanceService.findAttendanceByKlassSeminarId(klassSeminarId);
    }
    @GetMapping(value = "{klassSeminarId}/info")
    public List<AttendanceInfo> findAttendanceInfoByKlassSeminarId(@PathVariable("klassSeminarId") Long klassSeminarId){
        return attendanceService.findAttendanceInfoByKlassSeminarId(klassSeminarId);
    }
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
    @DeleteMapping(value = "{attendanceId}")
    public HttpStatus deleteAttendanceById(@PathVariable("attendanceId") Long id){
        HttpStatus httpStatus=(attendanceService.deleteAttendanceById(id)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }
}
