package com.rest.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamStudentDao {
    public Long findByStudentId(Long studentId);
}
