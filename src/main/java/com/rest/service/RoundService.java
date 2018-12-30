package com.rest.service;

import com.rest.dao.KlassRoundDao;
import com.rest.dao.KlassSeminarDao;
import com.rest.dao.RoundDao;
import com.rest.dao.SeminarDao;
import com.rest.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoundService {
    @Autowired
    private RoundDao roundDao;
    @Autowired
    private KlassRoundDao klassRoundDao;
    @Autowired
    private SeminarDao seminarDao;
    @Autowired
    private KlassSeminarDao klassSeminarDao;
    public int saveRound(Round round){
        List<Round> roundList=roundDao.findByCourseId(round.getCourseId());
        if(roundList.isEmpty()){
            Integer integer=new Integer(1);
            round.setRoundSerial(integer);
        }
        else {
            Integer integer=new Integer(0);
            for(Round r:roundList){
                if (r.getRoundSerial()>integer){
                    integer=r.getRoundSerial();
                }
            }
            round.setRoundSerial(integer+1);
        }
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

    /**
     * 需求roundList
     * @param courseId
     * @return
     */
    public List<RoundList> findListByCourseId(Long courseId){
        List<Round> roundList=roundDao.findByCourseId(courseId);
        List<RoundList> roundLists=new ArrayList<RoundList>();
        for (Round round:roundList){
            RoundList roundList1=new RoundList(round);
            Long roundId=roundList1.getRoundId();
            List<SeminarList> seminarListList=new ArrayList<SeminarList>();
            List<Seminar> seminars=seminarDao.findByRoundId(roundId);
            for (Seminar seminar:seminars){
                SeminarList seminarList=new SeminarList(seminar);
                Long seminarId=seminar.getId();
                List<KlassSeminar> klassSeminars=klassSeminarDao.findBySeminar(seminarId);
                seminarList.setKlassSeminarList(klassSeminars);
                seminarListList.add(seminarList);
            }
            roundList1.setSeminarLists(seminarListList);
            roundLists.add(roundList1);
        }
        return roundLists;
    }
    public List<KlassRound> findKlassRound(Long klassId){
        return klassRoundDao.findByKlassId(klassId);
    }
    public int deleteKlassRoundByRoundId(Long roundId){
        return klassRoundDao.deleteByRoundId(roundId);
    }
    public int deleteRound(Long roundId){
        Round round=roundDao.find(roundId);
        Long courseId=round.getCourseId();
        if(roundDao.deleteById(roundId)==1){
            klassRoundDao.deleteByRoundId(roundId);
            List<Round> roundList=roundDao.findByCourseId(courseId);
            if(!roundList.isEmpty()){
                for(Round round1:roundList){
                    if(round1.getRoundSerial()>round.getRoundSerial()){
                        roundDao.updateSerial(round1.getId(),round1.getRoundSerial()-1);
                    }
                }
            }
            return 1;
        }
        else {
            return 0;
        }
    }
    public Integer findEnrollNumber(Long roundId,Long klassId){
        return klassRoundDao.findByRoundIdAndClassId(roundId,klassId);
    }
    public int updateSerial(Long id,Integer serial)
    {
        return roundDao.updateSerial(id,serial);
    }
    public int save(KlassRound klassRound){
        return klassRoundDao.save(klassRound);
    }
}
