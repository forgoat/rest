package com.rest.controller;

import com.rest.entity.Attendance;
import com.rest.entity.AttendanceInfo;
import com.rest.entity.AttendanceInfomation;
import com.rest.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
