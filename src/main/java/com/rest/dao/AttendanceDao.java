package com.rest.dao;

import com.rest.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendanceDao {
    public List<Attendance> findAttendanceByKlassSeminarId(Long klassSeminarId);
    public int saveAttendance(Attendance attendance);
}
