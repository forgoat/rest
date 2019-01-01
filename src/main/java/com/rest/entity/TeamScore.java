package com.rest.entity;

import java.util.List;

public class TeamScore {
    private RoundScore roundScore;
    private List<Seminar> seminarList;
    private List<SeminarScore>seminarScoreList;

    public TeamScore() {
    }

    public TeamScore(ScorePage scorePage){
        roundScore=scorePage.getRoundScore();
        seminarList=scorePage.getSeminarList();
        seminarScoreList=scorePage.getSeminarScoreList();
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
        return "TeamScore{" +
                "roundScore=" + roundScore +
                ", seminarList=" + seminarList +
                ", seminarScoreList=" + seminarScoreList +
                '}';
    }
}
