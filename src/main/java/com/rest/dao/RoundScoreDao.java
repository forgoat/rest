package com.rest.dao;

import com.rest.entity.Round;
import com.rest.entity.RoundScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoundScoreDao {
    RoundScore queryByRoundIdTeamId(@Param("roundId")Long roundId, @Param("teamId")Long teamId);
    public RoundScore findRoundByRoundIdAndTeamId(@Param("roundId") Long roundId,@Param("teamId") Long teamId);
}
