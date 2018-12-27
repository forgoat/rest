package com.rest.dao;

import com.rest.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuestionDao {
    public int saveQuestion(Question question);
    public int gradeQuestion(@Param("questionId") Long questionId, @Param("score") double score);
    public Question findQuestionById(Long questionId);
}
