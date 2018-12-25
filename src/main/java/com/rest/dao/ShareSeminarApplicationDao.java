package com.rest.dao;

import com.rest.entity.ShareSeminarApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShareSeminarApplicationDao {
    public int save(ShareSeminarApplication shareSeminarApplication);
    public List<ShareSeminarApplication> findByMainCourseIdOrSubCourseId(Long courseId);
    public int acceptSeminarShare(Long shareSeminarId);
}
