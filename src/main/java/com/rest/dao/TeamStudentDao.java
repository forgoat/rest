package com.rest.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamStudentDao {
    Long findByStudentId(Long studentId);

    List<Long> queryByTeamId(Long teamId);

}
