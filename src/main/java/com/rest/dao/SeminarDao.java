package com.rest.dao;

import com.rest.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

@Mapper
public interface SeminarDao {
    public int save(Seminar seminar);
    public List<Seminar> findByRoundId(Long roundId);
    public Seminar findById(Long id);
    public int delete(Long id);
    public List<Seminar> findAll();
    public List<Seminar> findByCourseIdAndRoundId(@Param("courseId") Long courseId,@Param("roundId") Long roundId);
    public int updateSelective(@Param("id") Long id, @Param("courseId") Long courseId, @Param("roundId") Long roundId, @Param("seminarName") String seminarName, @Param("introduction") String introduction, @Param("maxTeam") Integer maxTeam,@Param("isVisible") Integer isVisible, @Param("seminarSerial") Integer seminarSerial, @Param("enrollStartTime") Date enrollStartTime,@Param("enrollEndTime") Date enrollEndTime);
}
