package com.rest.service;

import com.rest.dao.AttendanceDao;
import com.rest.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;
    public List<Attendance> findAttendanceByKlassSeminarId(Long klassSeminarId){
        return attendanceDao.findAttendanceByKlassSeminarId(klassSeminarId);
    }
}
