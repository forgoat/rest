package com.rest.mapper;

import com.rest.entity.RoundScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoundScoreMapper {
    RoundScore queryByRoundIdTeamId(@Param("roundId")Long roundId, @Param("teamId")Long teamId);
    public RoundScore findRoundByRoundIdAndTeamId(@Param("roundId") Long roundId,@Param("teamId") Long teamId);
}
