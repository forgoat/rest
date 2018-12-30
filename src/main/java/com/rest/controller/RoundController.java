package com.rest.controller;

import com.rest.entity.*;
import com.rest.po.Klass;
import com.rest.po.KlassRound;
import com.rest.po.Round;
import com.rest.po.Seminar;
import com.rest.service.KlassService;
import com.rest.service.RoundService;
import com.rest.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "round")
public class RoundController {
    @Autowired
    private RoundService roundService;
    @Autowired
    private SeminarService seminarService;
    @Autowired
    private KlassService klassService;

    /**
     * 保存轮次
     * @param round
     * @return
     */
    @PostMapping(value = "")
    public ResponseEntity<Long> saveRound(Round round){
        List<Klass> klassList=klassService.findByCourseId(round.getCourseId());
        if(roundService.saveRound(round)==1){
            for(Klass klass:klassList){
                KlassRound klassRound=new KlassRound();
                klassRound.setEnrollNumber(1);
                klassRound.setKlassId(klass.getId());
                klassRound.setRoundId(round.getId());
                roundService.save(klassRound);
            }
            return new ResponseEntity<Long>(round.getId(), HttpStatus.ACCEPTED);
        }
        else {
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping(value = "klassRound")
    public int saveKlassRound(KlassRound klassRound){
        return roundService.save(klassRound);
    }

    /**
     * 获取轮次
     * @param id
     * @return
     */
    @GetMapping(value = "{roundId}")
    public ResponseEntity<Round> find(@PathVariable("roundId") Long id){
        Round round=roundService.find(id);
        HttpStatus httpStatus=(round!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<Round>(round,httpStatus);
    }

    /**
     * 修改轮次
     * @param id
     * @param presentationScoreMethod
     * @param reportScoreMethod
     * @param questionScoreMethod
     * @return
     */
    @PutMapping(value = "{roundId}")
    public HttpStatus updateInfo(@PathVariable("roundId")Long id,Integer presentationScoreMethod	,Integer reportScoreMethod,Integer questionScoreMethod){
        if(roundService.updateInfo(id,presentationScoreMethod,reportScoreMethod,questionScoreMethod)==1){
            return HttpStatus.ACCEPTED;
        }
        else {
            return HttpStatus.FORBIDDEN;
        }
    }

    /**
     * 查找轮次下的讨论课
     * @param roundId
     * @return
     */
    @GetMapping(value = "{roundId}/seminar")
    public ResponseEntity<List<Seminar>> findRoundSeminar(@PathVariable("roundId") Long roundId){
        List<Seminar> seminarList=seminarService.findByRoundId(roundId);
        HttpStatus httpStatus=(seminarList.isEmpty())?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity<List<Seminar>>(seminarList,httpStatus);
    }
    @GetMapping(value = "findKlassRound")
    public List<KlassRound> findKlassRound(Long klassId)
    {
        return roundService.findKlassRound(klassId);
    }

    /**
     * 删除轮次
     * @param roundId
     * @return
     */
    @DeleteMapping(value = "{roundId}")
    public HttpStatus deleteByRoundId(@PathVariable("roundId") Long roundId){
        if (roundService.deleteRound(roundId)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    @GetMapping(value = "findByCourseId")
    public List<Round> findByCourseId(Long courseId){
        return roundService.findByCourseId(courseId);
    }
    @GetMapping(value = "findEnrollNumber")
    public Integer findEnrollNumber(Long roundId,Long klassId){
        return roundService.findEnrollNumber(roundId,klassId);
    }
    @PutMapping(value = "updateSerial")
    public int updateSerial(Long id,Integer serial){
        return roundService.updateSerial(id,serial);
    }

    /**
     * 需求list
     * @param courseId
     * @return
     */
    @GetMapping(value = "/course/{courseId}")
    public List<RoundList> roundListList(@PathVariable("courseId") Long courseId){
        return roundService.findListByCourseId(courseId);
    }
}
