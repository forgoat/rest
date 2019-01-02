package com.rest.dao;

import com.rest.entity.TeamValidApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeamValidApplicationDao {
    int saveTeamValidApplication(TeamValidApplication teamValidApplication);
    int insertTeamValidApplication(@Param("teamId")Long teamId,@Param("teacherId")Long teacherId,@Param("status")Integer status);
}
