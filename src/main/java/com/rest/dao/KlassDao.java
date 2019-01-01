package com.rest.dao;

import com.rest.entity.Klass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KlassDao {
    int saveKlass(Klass klass);
    public Klass findKlassById(Long id);
    public int deleteById(Long id);
    public List<Klass> findByCourseId(Long courseId);
    public  List<Klass> queryByCourseId(@Param("courseId")Long courseId);
}
