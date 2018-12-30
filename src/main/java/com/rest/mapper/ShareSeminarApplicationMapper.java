package com.rest.mapper;

import com.rest.po.ShareSeminarApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShareSeminarApplicationMapper {
    public int save(ShareSeminarApplication shareSeminarApplication);
    public List<ShareSeminarApplication> findByMainCourseIdOrSubCourseId(Long courseId);
    public int acceptSeminarShare(Long shareSeminarId);
    public ShareSeminarApplication findById(Long id);
    public int reject(Long shareSeminarId);
    public List<ShareSeminarApplication> findByCourseId(Long courseId);
}
