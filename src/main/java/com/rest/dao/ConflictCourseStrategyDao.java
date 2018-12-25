package com.rest.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ConflictCourseStrategyDao {
    public Long findId(Long courseId);
    public List<Long> findConflict(Long id);
}
