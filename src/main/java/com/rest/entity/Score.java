package com.rest.entity;

public class Score {
    private float seminar_total_score;
    private float round_total_score;
    private  float presentaton_score;
    private  float question_score;
    private  float report_score;

    public float getSeminar_total_score() {
        return seminar_total_score;
    }

    public void setSeminar_total_score(float seminar_total_score) {
        this.seminar_total_score = seminar_total_score;
    }

    public float getRound_total_score() {
        return round_total_score;
    }

    public void setRound_total_score(float round_total_score) {
        this.round_total_score = round_total_score;
    }

    public float getPresentaton_score() {
        return presentaton_score;
    }

    public void setPresentaton_score(float presentaton_score) {
        this.presentaton_score = presentaton_score;
    }

    public float getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(float question_score) {
        this.question_score = question_score;
    }

    public float getReport_score() {
        return report_score;
    }

    public void setReport_score(float report_score) {
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
