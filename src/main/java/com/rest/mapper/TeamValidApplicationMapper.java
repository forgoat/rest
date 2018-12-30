package com.rest.mapper;

import com.rest.entity.TeamValidApplication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamValidApplicationMapper {
    int saveTeamValidApplication(TeamValidApplication teamValidApplication);
}
