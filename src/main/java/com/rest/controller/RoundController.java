package com.rest.controller;

import com.rest.entity.KlassRound;
import com.rest.entity.Round;
import com.rest.entity.Seminar;
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

    /**
     * 保存轮次
     * @param round
     * @return
     */
    @PostMapping(value = "")
    public ResponseEntity<Long> saveRound(Round round){
        if(roundService.saveRound(round)==1){
            return new ResponseEntity<Long>(round.getId(), HttpStatus.ACCEPTED);
        }
        else {
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.FORBIDDEN);
        }
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
    public List<KlassRound> findKlassRound(Long klassId){
        return roundService.findKlassRound(klassId);
    }
}
