package com.rest.dao;

import com.rest.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoundDao {
    public int saveRound(Round round);
    public Round find(Long id);
    public int updateRoundSelective(@Param("id")Long id,@Param("presentation_score_method")Integer presentation_score_method,@Param("report_score_method")Integer report_score_method,@Param("question_score_method")Integer question_score_method);
}
