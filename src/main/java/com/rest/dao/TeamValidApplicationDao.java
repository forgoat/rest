package com.rest.dao;

import com.rest.entity.TeamValidApplication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamValidApplicationDao {
    int saveTeamValidApplication(TeamValidApplication teamValidApplication);
}
