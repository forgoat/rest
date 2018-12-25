package com.rest.dao;

import com.rest.entity.KlassRound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface KlassRoundDao {
    public List<KlassRound> findByKlassId(Long klassId);
}
