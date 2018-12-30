package com.rest.mapper;

import com.rest.po.KlassTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KlassTeamMapper {
    public List<Long> findByTeamId(Long teamId);
    public int deleteKlassTeamsByTeamId(Long teamId);
    public int save(KlassTeam klassTeam);
    public List<KlassTeam> findByKlassId(Long klassId);
    public int deleteByTeamIdAndKlassId(@Param("teamId") Long teamId,@Param("klassId") Long klassId);
}
