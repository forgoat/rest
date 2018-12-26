package com.rest.dao;

import com.rest.entity.KlassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KlassStudentDao {
    public Long findKlass(@Param("studentId") Long studentId,@Param("courseId") Long courseId);
     List<KlassStudent> findByTeamId(Long teamId);
     int quitTeam(Long studentId);
     KlassStudent findByStudentId(Long studentId);
     int updateTeam(@Param("studentId") Long studentId,@Param("teamId") Long teamId);
     Long findTeam(@Param("courseId")Long courseId,@Param("studentId")Long studentId);
}
