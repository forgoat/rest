package com.rest.controller;

import com.rest.entity.SeminarScore;
import com.rest.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "seminarscore")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 保存成绩
     * @param seminarScore
     * @return
     */
    @PostMapping(value = "")
    public HttpStatus saveSeminarScore(SeminarScore seminarScore){
        if(scoreService.saveSeminarScore(seminarScore)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.FORBIDDEN;
        }
    }

    /**
     * 查找小组的讨论课成绩
     * @param teamId
     * @param klassSeminarId
     * @return
     */
    @GetMapping(value = "{seminarId}/team/{teamId}")
    public ResponseEntity<SeminarScore> seeScore(@PathVariable("teamId") Long teamId, @PathVariable("seminarId") Long klassSeminarId){
        SeminarScore seminarScore=scoreService.findByTeamIdAndSeminarId(teamId,klassSeminarId);
        HttpStatus httpStatus=(seminarScore!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<SeminarScore>(seminarScore,httpStatus);
    }

    /**
     * 获取讨论课的所有分组成绩
     * @param classSeminarId
     * @return
     */
    @GetMapping(value = "{classSeminarId}")
    public ResponseEntity<List<SeminarScore>> seeSeminarScore(@PathVariable("classSeminarId")Long classSeminarId){
        List<SeminarScore> seminarScoreList=scoreService.findByKlassSeminarId(classSeminarId);
        HttpStatus httpStatus=HttpStatus.OK;
        return new ResponseEntity<List<SeminarScore>>(seminarScoreList,httpStatus);
    }
}