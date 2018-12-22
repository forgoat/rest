package com.rest.controller;

import com.rest.entity.Round;
import com.rest.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "round")
public class RoundController {
    @Autowired
    private RoundService roundService;
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
    @GetMapping(value = "{roundId}")
    public ResponseEntity<Round> find(@PathVariable("roundId") Long id){
        Round round=roundService.find(id);
        HttpStatus httpStatus=(round!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<Round>(round,httpStatus);
    }
    @PutMapping(value = "{roundId}")
    public HttpStatus updateInfo(@PathVariable("roundId")Long id,Integer presentation_score_method	,Integer report_score_method,Integer question_score_method){
        if(roundService.updateInfo(id,presentation_score_method,report_score_method,question_score_method)==1){
            return HttpStatus.ACCEPTED;
        }
        else {
            return HttpStatus.FORBIDDEN;
        }
    }
}
