package com.rest.service;

import com.rest.dao.KlassRoundDao;
import com.rest.dao.RoundDao;
import com.rest.entity.KlassRound;
import com.rest.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundService {
    @Autowired
    private RoundDao roundDao;
    @Autowired
    private KlassRoundDao klassRoundDao;
    public int saveRound(Round round){
        return roundDao.saveRound(round);
    }
    public Round find(Long id){
        return roundDao.find(id);
    }
    public int updateInfo(Long id,Integer presentationScoreMethod,Integer reportScoreMethod,Integer questionScoreMethod){
        return roundDao.updateRoundSelective(id,presentationScoreMethod,reportScoreMethod,questionScoreMethod);
    }

    public List<Round> findByCourseId(Long courseId){
        return roundDao.findByCourseId(courseId);
    }
    public List<KlassRound> findKlassRound(Long klassId){
        return klassRoundDao.findByKlassId(klassId);
    }
    public int deleteKlassRoundByRoundId(Long roundId){
        return klassRoundDao.deleteByRoundId(roundId);
    }
    public int deleteRound(Long roundId){
        if(roundDao.deleteById(roundId)==1){
            klassRoundDao.deleteByRoundId(roundId);
            return 1;
        }
        else {
            return 0;
        }
    }
    public Integer findEnrollNumber(Long roundId,Long klassId){
        return klassRoundDao.findByRoundIdAndClassId(roundId,klassId);
    }
}
