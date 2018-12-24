package com.rest.controller;

import com.rest.entity.SeminarScore;
import com.rest.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "seminarscore")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @PostMapping(value = "")
    public HttpStatus saveSeminarScore(SeminarScore seminarScore){
        if(scoreService.saveSeminarScore(seminarScore)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.FORBIDDEN;
        }
    }
    @GetMapping(value = "{seminarId}/team/{teamId}",produces = "application/json;charset=UTF-8")
    public ResponseEntity<SeminarScore> seeScore(@PathVariable("teamId") Long teamId, @PathVariable("seminarId") Long klassSeminarId){
        SeminarScore seminarScore=scoreService.findByTeamIdAndSeminarId(teamId,klassSeminarId);
        HttpStatus httpStatus=(seminarScore!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<SeminarScore>(seminarScore,httpStatus);
    }
}
