package com.rest.dao;

import com.rest.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;

@Mapper
public interface SeminarDao {
    public int save(Seminar seminar);
    public Seminar findById(Long id);
    public int delete(Long id);
    public int updateSelective(@Param("id") Long id, @Param("course_id") Long course_id, @Param("round_id") Long round_id, @Param("seminar_name") String seminar_name, @Param("introducation") String introducation, @Param("max_team") Integer max_team,@Param("is_visible") Integer is_visible, @Param("seminar_serial") Integer seminar_serial, @Param("enroll_start_time") Date enroll_start_time,@Param("enroll_end_time") Date enroll_end_time);
}
