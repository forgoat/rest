package com.rest.dao;

import com.rest.entity.ShareTeamApplication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShareTeamApplicationDao {
    public int save(ShareTeamApplication shareTeamApplication);
}
