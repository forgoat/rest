package com.rest.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ConflictCourseStrategyMapper {
    public Long findId(Long courseId);
    public List<Long> findConflict(Long id);
}
