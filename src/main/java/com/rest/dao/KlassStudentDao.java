package com.rest.dao;

import com.rest.entity.KlassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KlassStudentDao {
    public List<KlassStudent> findByTeam_id(Long teamId);
    public int quitTeam(Long studentId);
    public KlassStudent findByStudent_id(Long studentId);
    public int updateTeam(@Param("studentId") Long studentId,@Param("teamId") Long teamId);
}
