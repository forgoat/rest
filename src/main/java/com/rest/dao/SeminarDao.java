package com.rest.dao;

import com.rest.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeminarDao {
    public int save(Seminar seminar);
}
