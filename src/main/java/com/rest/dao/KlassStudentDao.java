package com.rest.dao;

import com.rest.entity.KlassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KlassStudentDao {
    public List<KlassStudent> findByTeamId(Long teamId);
    public int quitTeam(Long studentId);
    public KlassStudent findByStudentId(Long studentId);
    public int updateTeam(@Param("studentId") Long studentId,@Param("teamId") Long teamId);
    public Long findTeam(@Param("courseId")Long courseId,@Param("studentId")Long studentId);
}
