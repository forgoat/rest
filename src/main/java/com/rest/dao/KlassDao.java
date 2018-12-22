package com.rest.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KlassDao {
    public int deleteById(Long id);
}
