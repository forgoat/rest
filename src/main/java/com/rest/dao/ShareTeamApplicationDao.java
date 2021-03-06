package com.rest.dao;

import com.rest.entity.ShareTeamApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShareTeamApplicationDao {
    public int save(ShareTeamApplication shareTeamApplication);
    public List<ShareTeamApplication> findBySubCourseId(Long courseId);
    public List<ShareTeamApplication> findByCourseId(Long courseId);
    public int acceptTeamShare(Long id);
    public int rejectTeamShare(Long id);
    public ShareTeamApplication findShareTeamApplication(Long id);
    public List<ShareTeamApplication> findAll();
}
