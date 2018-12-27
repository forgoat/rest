package com.rest.dao;

import com.rest.entity.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionDao {
    public int saveQuestion(Question question);
}
