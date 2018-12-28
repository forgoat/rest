package com.rest.entity;

public class Score {
    private Double seminarTotalScore;
    private Double roundTotalScore;
    private Double presentatonScore;
    private Double questionScore;
    private Double reportScore;

    public Score() {
    }

    public Double getSeminarTotalScore() {
        return seminarTotalScore;
    }

    public void setSeminarTotalScore(Double seminarTotalScore) {
        this.seminarTotalScore = seminarTotalScore;
    }

    public Double getRoundTotalScore() {
        return roundTotalScore;
    }

    public void setRoundTotalScore(Double roundTotalScore) {
        this.roundTotalScore = roundTotalScore;
    }

    public Double getPresentatonScore() {
        return presentatonScore;
    }

    public void setPresentatonScore(Double presentatonScore) {
        this.presentatonScore = presentatonScore;
    }

    public Double getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Double questionScore) {
        this.questionScore = questionScore;
    }

    public Double getReportScore() {
        return reportScore;
    }

    public void setReportScore(Double reportScore) {
        this.reportScore = reportScore;
    }
}
