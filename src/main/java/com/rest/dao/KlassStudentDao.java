package com.rest.dao;

import com.rest.entity.KlassStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KlassStudentDao {
    public List<KlassStudent> findByTeam_id(Long teamId);
}
