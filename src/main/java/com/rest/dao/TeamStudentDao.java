package com.rest.dao;

import com.rest.entity.TeamStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamStudentDao {
    Long findByStudentId(@Param("studentId") Long studentId);

    List<Long> queryByTeamId(Long teamId);
    public int deleteByTeamId(Long teamId);
    public List<TeamStudent> findByTeamId(Long teamId);

    int batchInsertTeamStudent(@Param("teamStudentList") List<TeamStudent> teamStudentList);
    int deleteByStudentId(@Param("studentId") Long studentId);

}
