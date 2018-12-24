package com.rest.dao;

import com.rest.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoundDao {
    public int saveRound(Round round);
    public Round find(Long id);
    public int updateRoundSelective(@Param("id")Long id,@Param("presentationScoreMethod")Integer presentationScoreMethod,@Param("reportScoreMethod")Integer reportScoreMethod,@Param("questionScoreMethod")Integer questionScoreMethod);
}
