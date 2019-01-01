package com.rest.dao;

import com.rest.entity.KlassTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KlassTeamDao {
    public List<Long> findByTeamId(Long teamId);
    public int deleteKlassTeamsByTeamId(Long teamId);
    public int saveKlassTeam(@Param("klassId")Long klassId,@Param("teamId")Long teamId);
    int save(KlassTeam klassTeam);
    public List<KlassTeam> findByKlassId(Long klassId);
    public int deleteByTeamIdAndKlassId(@Param("teamId") Long teamId,@Param("klassId") Long klassId);
}
