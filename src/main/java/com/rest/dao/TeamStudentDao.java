package com.rest.dao;

import com.rest.entity.Student;
import com.rest.entity.TeamStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Mapper
public interface TeamStudentDao {
    public Long findByStudentId(Long studentId);
    public int deleteByTeamId(Long teamId);
    public List<TeamStudent> findByTeamId(Long teamId);
}
