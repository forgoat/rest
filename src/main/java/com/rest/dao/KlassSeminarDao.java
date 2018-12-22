package com.rest.dao;

import com.rest.entity.Klass_seminar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface KlassSeminarDao {
    public List<Klass_seminar> findBySeminar(Long seminar_id);
    public int save(Klass_seminar klass_seminar);
    public int delete(Long id);
}
