package com.rest.dao;

import com.rest.entity.TeamStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamStudentDao {
    Long findByStudentId(Long studentId);

    List<Long> queryByTeamId(Long teamId);
    public int deleteByTeamId(Long teamId);
    public List<TeamStudent> findByTeamId(Long teamId);
}
