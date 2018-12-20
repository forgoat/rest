package com.rest.dao;

import com.rest.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ScoreDao {


    List<Score> queryScore(@Param("course_id") BigInteger course_id,
                           @Param("student_id") BigInteger student_id,
                           @Param("round_id") BigInteger round_id,
                           @Param("seminar_id") BigInteger seminar_id);

}
