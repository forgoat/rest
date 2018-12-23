package com.rest.dao;

import com.rest.entity.ShareSeminarApplication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShareSeminarApplicationDao {
    public int save(ShareSeminarApplication shareSeminarApplication);
}
