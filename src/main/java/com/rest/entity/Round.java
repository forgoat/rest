package com.rest.entity;

import java.math.BigInteger;

public class Round {
    private Long id;
    private Long course_id;
    private Integer round_serial;
    private Integer presentation_score_method;
    private Integer report_score_method;
    private Integer question_score_method;

    public Round() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Integer getRound_serial() {
        return round_serial;
    }

    public void setRound_serial(Integer round_serial) {
        this.round_serial = round_serial;
    }

    public Integer getPresentation_score_method() {
        return presentation_score_method;
    }

    public void setPresentation_score_method(Integer presentation_score_method) {
        this.presentation_score_method = presentation_score_method;
    }

    public Integer getReport_score_method() {
        return report_score_method;
    }

    public void setReport_score_method(Integer report_score_method) {
        this.report_score_method = report_score_method;
    }

    public Integer getQuestion_score_method() {
        return question_score_method;
    }

    public void setQuestion_score_method(Integer question_score_method) {
        this.question_score_method = question_score_method;
    }
}
