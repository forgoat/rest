package com.rest.dao;

import com.rest.entity.CourseMemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface CourseMemberLimitStrategyDao {

    CourseMemberLimitStrategy queryCourseMemberLimitStrategy(@Param("id")Long id);

    int insertCourseMemberLimitStrategy(@Param(value = "courseId")Long courseId,@Param(value = "minMember")Integer minMember,@Param(value = "maxMember")Integer maxMember);

    List<CourseMemberLimitStrategy> queryAllCourseMemberLimitStrategy();
}
