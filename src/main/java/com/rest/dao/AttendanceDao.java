package com.rest.dao;

import com.rest.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttendanceDao {
    public List<Attendance> findAttendanceByKlassSeminarId(Long klassSeminarId);
    public int saveAttendance(Attendance attendance);
    public int deleteById(Long attendanceId);
    public int updateAttendanceStatus(@Param("attendanceId") Long attendanceId,@Param("status") Integer status);
}
