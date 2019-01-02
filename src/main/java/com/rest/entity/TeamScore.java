package com.rest.entity;

import java.util.List;

public class TeamScore {
    private RoundScore roundScore;
    private List<SeminarScore>seminarScoreList;

    public TeamScore() {
    }


    public RoundScore getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(RoundScore roundScore) {
        this.roundScore = roundScore;
    }


    public List<SeminarScore> getSeminarScoreList() {
        return seminarScoreList;
    }

    public void setSeminarScoreList(List<SeminarScore> seminarScoreList) {
        this.seminarScoreList = seminarScoreList;
    }

    @Override
    public String toString() {
        return "TeamScore{" +
                "roundScore=" + roundScore +
                ", seminarScoreList=" + seminarScoreList +
                '}';
    }
}
