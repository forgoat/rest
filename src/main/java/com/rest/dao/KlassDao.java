package com.rest.dao;

import com.rest.entity.Klass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KlassDao {
    int saveKlass(Klass klass);
    public int deleteById(Long id);
    public List<Klass> findByCourseId(Long courseId);

}
