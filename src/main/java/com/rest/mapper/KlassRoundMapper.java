package com.rest.mapper;

import com.rest.po.KlassRound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface KlassRoundMapper {
    public List<KlassRound> findByKlassId(@Param("klassId") Long klassId);
    public Integer findByRoundIdAndClassId(@Param("roundId") Long roundId,@Param("klassId") Long klassId);
    public int deleteByRoundId(Long roundId);
    public int save(KlassRound klassRound);
}
