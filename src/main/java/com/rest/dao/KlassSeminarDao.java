package com.rest.dao;

import com.rest.entity.Klass_seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
@Mapper
public interface KlassSeminarDao {
    public List<Klass_seminar> findBySeminar(Long seminar_id);
    public int save(Klass_seminar klass_seminar);
    public int delete(Long id);
    public int changeStatus(@Param("seminar_id") Long seminar_id, @Param("class_id") Long class_id, @Param("status") Integer status);
    public int changeddl(@Param("seminar_id")Long seminar_id, @Param("class_id")Long class_id, @Param("report_ddl")Date report_ddl);
}
