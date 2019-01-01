package com.rest.controller;

import com.rest.entity.Round;
import com.rest.entity.RoundScorePage;
import com.rest.entity.ScorePage;
import com.rest.entity.SeminarScore;
import com.rest.service.RoundService;
import com.rest.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private RoundService roundService;

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
        System.out.println("queryScorePage:");
        ScorePage scorePage=new ScorePage();
        if(scoreService.queryScorePage(courseId,studentId,roundSerial)!=null)
            scorePage=scoreService.queryScorePage(courseId,studentId,roundSerial);
        HttpStatus httpStatus=HttpStatus.OK;
        if(scorePage!=null)
        return new ResponseEntity<ScorePage>(scorePage,httpStatus);
        else return null;
    }


    /**
     * 修改讨论课提问分数
     * @param klassSeminarId
     * @param teamId
     * @param questionScore
     * @return
     */
    @PutMapping(value = "questionScore")
    public HttpStatus updateSeminarQuestionScore(Long klassSeminarId,Long teamId,Integer questionScore){
        HttpStatus httpStatus=(scoreService.updateSeminarQuestionScore(klassSeminarId,teamId,questionScore)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }

    /**
     * 修改讨论课展示成绩
     * @param klassSeminarId
     * @param teamId
     * @param presentationScore
     * @return
     */
    @PutMapping(value = "presentationScore")
    public HttpStatus updateSeminarScore(Long klassSeminarId,Long teamId,double presentationScore){
        HttpStatus httpStatus=(scoreService.updateSeminarScore(klassSeminarId,teamId,presentationScore)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }

    /**
     * 修改讨论课报告成绩
     * @param klassSeminarId
     * @param teamId
     * @param reportScore
     * @return
     */
    @PutMapping(value = "reportScore")
    public HttpStatus updateReportScore(Long klassSeminarId,Long teamId,double reportScore){
        HttpStatus httpStatus=(scoreService.updateReportScore(klassSeminarId,teamId,reportScore)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }

    /**
     * 查看轮次总分
     * @param roundId
     * @param teamId
     * @return
     */
    @GetMapping(value = "roundScore")
    public Double roundTotalScore(Long roundId,Long teamId,Long courseId){
        Double score=scoreService.findRoundScore(courseId,roundId,teamId);
        return score;
    }

    /**
     * 动态修改讨论课成绩
     * @param klassSeminarId
     * @param teamId
     * @param presentationScore
     * @param questionScore
     * @param reportScore
     * @return
     */
    @PutMapping(value = "seminarScore")
    public HttpStatus updateSeminarScore(Long klassSeminarId,Long teamId,Double presentationScore,Double questionScore,Double reportScore){
        HttpStatus httpStatus=(scoreService.updateSeminarScore(klassSeminarId,teamId,presentationScore,questionScore,reportScore)==1)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return httpStatus;
    }

    /**
     * 查找提问成绩
     * @param klassSeminarId
     * @param teamId
     * @return
     */
    @GetMapping("questionScoreList")
    public List<Double> findQuestionScores(Long klassSeminarId,Long teamId){
        return scoreService.findQuestionScore(klassSeminarId,teamId);
    }
    /**
     * 查找队伍的所在班级
     * @param courseId
     * @param teamId
     * @return
     */
    @GetMapping(value = "klass")
    public Long findKlassForTeam(Long courseId,Long teamId){
        return scoreService.findKlassForTeam(courseId,teamId);
    }

    public List<RoundScorePage> teacherFindScore(Long courseId){
        List<RoundScorePage> roundScorePageList=new ArrayList<>();
        List<Round> roundList=roundService.findByCourseId(courseId);
        if (roundList.isEmpty()){
            System.out.println("No round");
        }
        return roundScorePageList;
    }
}
