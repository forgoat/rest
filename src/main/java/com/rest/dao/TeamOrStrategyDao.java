package com.rest.dao;

import com.rest.entity.TeamOrStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamOrStrategyDao {

    List<TeamOrStrategy> queryTeamOrStrategy(@Param("id")Long id);

    int insertTeamOrStrategy(@Param("id")Long id,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);

    List<TeamOrStrategy> queryAllTeamOrStrategy();
}
