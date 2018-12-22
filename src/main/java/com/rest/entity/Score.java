package com.rest.entity;

public class Score {
    private Number seminar_total_score;
    private Number round_total_score;
    private  Number presentaton_score;
    private  Number question_score;
    private  Number report_score;

    public Score() {
    }

    public Number getSeminar_total_score() {
        return seminar_total_score;
    }

    public void setSeminar_total_score(Number seminar_total_score) {
        this.seminar_total_score = seminar_total_score;
    }

    public Number getRound_total_score() {
        return round_total_score;
    }

    public void setRound_total_score(Number round_total_score) {
        this.round_total_score = round_total_score;
    }

    public Number getPresentaton_score() {
        return presentaton_score;
    }

    public void setPresentaton_score(Number presentaton_score) {
        this.presentaton_score = presentaton_score;
    }

    public Number getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(Number question_score) {
        this.question_score = question_score;
    }

    public Number getReport_score() {
        return report_score;
    }

    public void setReport_score(Number report_score) {
        this.report_score = report_score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "seminar_total_score=" + seminar_total_score +
                ", round_total_score=" + round_total_score +
                ", presentaton_score=" + presentaton_score +
                ", question_score=" + question_score +
                ", report_score=" + report_score +
                '}';
    }
}
