package com.rest.controller;

import com.rest.entity.ScorePage;
import com.rest.entity.SeminarScore;
import com.rest.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "score")
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

    /**
     * 此课程所有的轮次序列号 例如：第几次讨论课
     * @param courseId
     * @return
     */
    @GetMapping(value = "queryAllRoundSerialByCourseId")
    public ResponseEntity<List<Integer>> queryAllRoundSerialByCourseId(Long courseId){
        List<Integer> roundSerialList=scoreService.queryAllRoundSerialByCourseId(courseId);
        HttpStatus httpStatus=HttpStatus.OK;
        return new ResponseEntity<List<Integer>>(roundSerialList,httpStatus);
    }

    /**
     * 查询ScorePage所需信息
     * @param courseId
     * @param studentId
     * @param roundSerial
     * @return
     */
    @GetMapping(value = "queryScorePage")
    public ResponseEntity<ScorePage> queryScorePage(Long courseId,Long studentId,Integer roundSerial){
        ScorePage scorePage=scoreService.queryScorePage(courseId,studentId,roundSerial);
        HttpStatus httpStatus=HttpStatus.OK;
        return new ResponseEntity<ScorePage>(scorePage,httpStatus);
    }

}
