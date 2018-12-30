package com.rest.mapper;

import com.rest.po.ShareTeamApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShareTeamApplicationMapper {
    public int save(ShareTeamApplication shareTeamApplication);
    public List<ShareTeamApplication> findBySubCourseId(Long courseId);
    public List<ShareTeamApplication> findByCourseId(Long courseId);
    public int acceptTeamShare(Long id);
    public int rejectTeamShare(Long id);
    public ShareTeamApplication findShareTeamApplication(Long id);
    public List<ShareTeamApplication> findAll();
}
