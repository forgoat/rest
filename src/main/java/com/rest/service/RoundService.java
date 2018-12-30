package com.rest.service;

import com.rest.mapper.KlassRoundMapper;
import com.rest.mapper.KlassSeminarMapper;
import com.rest.mapper.RoundMapper;
import com.rest.mapper.SeminarMapper;
import com.rest.entity.*;
import com.rest.po.KlassRound;
import com.rest.po.KlassSeminar;
import com.rest.po.Round;
import com.rest.po.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoundService {
    @Autowired
    private RoundMapper roundMapper;
    @Autowired
    private KlassRoundMapper klassRoundMapper;
    @Autowired
    private SeminarMapper seminarMapper;
    @Autowired
    private KlassSeminarMapper klassSeminarMapper;
    public int saveRound(Round round){
        List<Round> roundList= roundMapper.findByCourseId(round.getCourseId());
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
        return roundMapper.saveRound(round);
    }
    public Round find(Long id){
        return roundMapper.find(id);
    }
    public int updateInfo(Long id,Integer presentationScoreMethod,Integer reportScoreMethod,Integer questionScoreMethod){
        return roundMapper.updateRoundSelective(id,presentationScoreMethod,reportScoreMethod,questionScoreMethod);
    }

    public List<Round> findByCourseId(Long courseId){
        return roundMapper.findByCourseId(courseId);
    }

    /**
     * 需求roundList
     * @param courseId
     * @return
     */
    public List<RoundList> findListByCourseId(Long courseId){
        List<Round> roundList= roundMapper.findByCourseId(courseId);
        List<RoundList> roundLists=new ArrayList<RoundList>();
        for (Round round:roundList){
            RoundList roundList1=new RoundList(round);
            Long roundId=roundList1.getRoundId();
            List<SeminarList> seminarListList=new ArrayList<SeminarList>();
            List<Seminar> seminars= seminarMapper.findByRoundId(roundId);
            for (Seminar seminar:seminars){
                SeminarList seminarList=new SeminarList(seminar);
                Long seminarId=seminar.getId();
                List<KlassSeminar> klassSeminars= klassSeminarMapper.findBySeminar(seminarId);
                seminarList.setKlassSeminarList(klassSeminars);
                seminarListList.add(seminarList);
            }
            roundList1.setSeminarLists(seminarListList);
            roundLists.add(roundList1);
        }
        return roundLists;
    }
    public List<KlassRound> findKlassRound(Long klassId){
        return klassRoundMapper.findByKlassId(klassId);
    }
    public int deleteKlassRoundByRoundId(Long roundId){
        return klassRoundMapper.deleteByRoundId(roundId);
    }
    public int deleteRound(Long roundId){
        Round round= roundMapper.find(roundId);
        Long courseId=round.getCourseId();
        if(roundMapper.deleteById(roundId)==1){
            klassRoundMapper.deleteByRoundId(roundId);
            List<Round> roundList= roundMapper.findByCourseId(courseId);
            if(!roundList.isEmpty()){
                for(Round round1:roundList){
                    if(round1.getRoundSerial()>round.getRoundSerial()){
                        roundMapper.updateSerial(round1.getId(),round1.getRoundSerial()-1);
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
        return klassRoundMapper.findByRoundIdAndClassId(roundId,klassId);
    }
    public int updateSerial(Long id,Integer serial)
    {
        return roundMapper.updateSerial(id,serial);
    }
    public int save(KlassRound klassRound){
        return klassRoundMapper.save(klassRound);
    }
}
