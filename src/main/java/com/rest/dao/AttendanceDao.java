package com.rest.dao;

import com.rest.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttendanceDao {
    public List<Attendance> findAttendanceByKlassSeminarId(Long klassSeminarId);
    public int saveAttendance(Attendance attendance);
    int updateAttendance(@Param("id") Long id,@Param("pptName") String pptName,@Param("pptUrl") String pptUrl);
    public int deleteById(Long attendanceId);
    Attendance queryByKlassSeminarIdAndTeamId(@Param("klassSeminarId")Long klassSeminarId,@Param("teamId")Long teamId);
    public int updateAttendanceStatus(@Param("attendanceId") Long attendanceId,@Param("status") Integer status);
    Attendance queryAttendanceById(Long id);
    public int savePPT(@Param("attendanceId") Long attendanceId,@Param("fileName") String fileName,@Param("path") String path);
    public Attendance findAttendanceByKlassSeminarIdAndTeamId(@Param("klassSeminarId") Long klassSeminarId,@Param("teamId") Long teamId);
}
