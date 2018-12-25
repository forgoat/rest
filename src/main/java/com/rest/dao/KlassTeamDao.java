package com.rest.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KlassTeamDao {
    public List<Long> findByTeamId(Long teamId);
}
