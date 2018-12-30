package com.rest.entity;

import java.util.List;
import java.util.Map;

public class ScorePage {
    private Long roundId;
    private Integer roundSerial;
    private RoundScore roundScore;
    private List<Seminar> seminarList;
    private List<SeminarScore>seminarScoreList;


    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public RoundScore getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(RoundScore roundScore) {
        this.roundScore = roundScore;
    }

    public List<Seminar> getSeminarList() {
        return seminarList;
    }

    public void setSeminarList(List<Seminar> seminarList) {
        this.seminarList = seminarList;
    }

    public List<SeminarScore> getSeminarScoreList() {
        return seminarScoreList;
    }

    public void setSeminarScoreList(List<SeminarScore> seminarScoreList) {
        this.seminarScoreList = seminarScoreList;
    }

    @Override
    public String toString() {
        return "ScorePage{" +
                "roundId=" + roundId +
                ", roundSerial=" + roundSerial +
                ", roundScore=" + roundScore +
                ", seminarList=" + seminarList +
                ", seminarScoreList=" + seminarScoreList +
                '}';
    }
}
