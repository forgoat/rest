package com.rest.dao;

import com.rest.entity.TeamStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamStrategyDao {

    List<TeamStrategy> queryTeamStrategy(@Param("courseId")Long courseId);

    int insertTeamStrategy(@Param("courseId")Long courseId,@Param("strategySerial")Integer strategySerial,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);
}
