package com.rest.dao;

import com.rest.entity.TeamValidApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamValidApplicationDao {
    public List<TeamValidApplication> findTeamValidApplicationByTeacherIdAndStatus(Long teacherId);
    int saveTeamValidApplication(TeamValidApplication teamValidApplication);
    int insertTeamValidApplication(@Param("teamId")Long teamId,@Param("teacherId")Long teacherId,@Param("status")Integer status);
    List<TeamValidApplication> queryAllTeamValidApplication();
}
