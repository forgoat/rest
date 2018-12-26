package com.rest.dao;

import com.rest.entity.KlassTeam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KlassTeamDao {
    public List<Long> findByTeamId(Long teamId);
    public int deleteKlassTeamsByTeamId(Long teamId);
    public int save(KlassTeam klassTeam);
}
