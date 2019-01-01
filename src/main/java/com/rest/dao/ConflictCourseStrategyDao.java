package com.rest.dao;

import com.rest.entity.ConflictCourseStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ConflictCourseStrategyDao {

    public Long findId(Long courseId);

    public List<Long> findConflict(Long id);

    List<ConflictCourseStrategy> queryConflictCourseStrategy(@Param("id")Long id);

    List<ConflictCourseStrategy> queryAllConflictCourseStrategy();

    int insertConflictCourseStrategy(@Param("id")Long id,@Param("courseId")Long courseId);
}
