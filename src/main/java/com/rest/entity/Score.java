package com.rest.entity;

public class Score {
    private double seminarTotalScore;
    private double roundTotalScore;
    private double presentatonScore;
    private double questionScore;
    private double reportScore;

    public Score() {
    }

    public double getSeminarTotalScore() {
        return seminarTotalScore;
    }

    public void setSeminarTotalScore(double seminarTotalScore) {
        this.seminarTotalScore = seminarTotalScore;
    }

    public double getRoundTotalScore() {
        return roundTotalScore;
    }

    public void setRoundTotalScore(double roundTotalScore) {
        this.roundTotalScore = roundTotalScore;
    }

    public double getPresentatonScore() {
        return presentatonScore;
    }

    public void setPresentatonScore(double presentatonScore) {
        this.presentatonScore = presentatonScore;
    }

    public double getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(double questionScore) {
        this.questionScore = questionScore;
    }

    public double getReportScore() {
        return reportScore;
    }

    public void setReportScore(double reportScore) {
        this.reportScore = reportScore;
    }
}
