package com.rest.dao;

import com.rest.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ScoreDao {

    List<Score>queryScore(BigInteger course_id,BigInteger student_id,BigInteger seminar_id,BigInteger round_id);

}
