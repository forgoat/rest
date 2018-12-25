package com.rest.dao;

import com.rest.entity.KlassRound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface KlassRoundDao {
    public List<KlassRound> findByKlassId(@Param("klassId") Long klassId);
    public Integer findByRoundIdAndClassId(@Param("roundId") Long roundId,@Param("klassId") Long klassId);
    public int deleteByRoundId(Long roundId);
}
