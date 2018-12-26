package com.rest.entity;

import java.util.List;
import java.util.Map;

public class ScorePage {
    private Long roundId;
    private String roundSerial;
    //讨论课成绩Map
    private Map<Seminar,SeminarScore> seminarScoreMap;
    private RoundScore roundScore;

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public String getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(String roundSerial) {
        this.roundSerial = roundSerial;
    }

    public Map<Seminar, SeminarScore> getSeminarScoreMap() {
        return seminarScoreMap;
    }

    public void setSeminarScoreMap(Map<Seminar, SeminarScore> seminarScoreMap) {
        this.seminarScoreMap = seminarScoreMap;
    }

    public RoundScore getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(RoundScore roundScore) {
        this.roundScore = roundScore;
    }
}
