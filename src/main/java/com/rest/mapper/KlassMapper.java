package com.rest.mapper;

import com.rest.po.Klass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KlassMapper {
    int saveKlass(Klass klass);
    public int deleteById(Long id);
    public List<Klass> findByCourseId(Long courseId);
}
