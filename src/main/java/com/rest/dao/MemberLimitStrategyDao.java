package com.rest.dao;

import com.rest.entity.MemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberLimitStrategyDao {

    MemberLimitStrategy queryMemberLimit(@Param("id")Long id);

    MemberLimitStrategy queryMemberLimitStrategyById(@Param("id")Long id);

    List<MemberLimitStrategy> queryAllMemberLimitStrategy();

    int insertMemberLimitStrategy(@Param("minMember")Integer minMember,@Param("maxMember")Integer maxMember);

}
