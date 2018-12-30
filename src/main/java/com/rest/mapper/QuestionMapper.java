package com.rest.mapper;

import com.rest.po.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    public int saveQuestion(Question question);
    public int selectQuestion(Long questionId);
    public int deleteQuestionList(@Param("klassSeminarId") Long klassSeminarId,@Param("attendanceId") Long attendanceId);
    public List<Question> questions(@Param("klassSeminarId") Long klassSeminarId,@Param("attendanceId") Long attendanceId);
    public List<Double> questionScores(@Param("KlassSeminarId") Long KlassSeminarId,@Param("teamId") Long teamId);
    public int gradeQuestion(@Param("questionId") Long questionId, @Param("score") double score);
    public Question findQuestionById(Long questionId);
    public List<Question> questionList(@Param("klassSeminarId") Long klassSeminarId,@Param("attendanceId") Long attendanceId);
}
