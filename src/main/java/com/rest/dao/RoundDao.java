package com.rest.dao;

import com.rest.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoundDao {
    public int saveRound(Round round);
    public Round find(Long id);
    public List<Round> findByCourseId(Long courseId);
    public int deleteById(Long id);
    public int updateSerial(@Param("id") Long id,@Param("serial") Integer serial);
    public int updateRoundSelective(@Param("id")Long id,@Param("presentationScoreMethod")Integer presentationScoreMethod,@Param("reportScoreMethod")Integer reportScoreMethod,@Param("questionScoreMethod")Integer questionScoreMethod);
}
