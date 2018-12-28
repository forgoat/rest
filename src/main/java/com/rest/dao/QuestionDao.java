package com.rest.dao;

import com.rest.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionDao {
    public int saveQuestion(Question question);
    public int selectQuestion(Long questionId);
    public List<Double> questionScores(@Param("KlassSeminarId") Long KlassSeminarId,@Param("teamId") Long teamId);
    public int gradeQuestion(@Param("questionId") Long questionId, @Param("score") double score);
    public Question findQuestionById(Long questionId);
    public List<Question> questionList(@Param("klassSeminarId") Long klassSeminarId,@Param("attendanceId") Long attendanceId);
}
