package com.rest.mapper;

import com.rest.entity.TeamStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamStudentMapper {
    Long findByStudentId(@Param("studentId") Long studentId);

    List<Long> queryByTeamId(Long teamId);
    public int deleteByTeamId(Long teamId);
    public List<TeamStudent> findByTeamId(Long teamId);

}
