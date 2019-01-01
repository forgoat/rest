package com.rest.dao;

import com.rest.entity.TeamAndStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamAndStrategyDao {

    List<TeamAndStrategy> queryTeamAndStrategy(@Param("id")Long id);

    int insertTeamAndStrategy(@Param("id")Long id,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);

    List<TeamAndStrategy> queryAllTeamAndStrategy();
}
