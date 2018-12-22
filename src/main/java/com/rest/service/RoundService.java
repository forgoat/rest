package com.rest.service;

import com.rest.dao.RoundDao;
import com.rest.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoundService {
    @Autowired
    private RoundDao roundDao;
    public int saveRound(Round round){
        return roundDao.saveRound(round);
    }
    public Round find(Long id){
        return roundDao.find(id);
    }
    public int updateInfo(Long id,Integer presentation_score_method,Integer report_score_method,Integer question_score_method){
        return roundDao.updateRoundSelective(id,presentation_score_method,report_score_method,question_score_method);
    }
}
