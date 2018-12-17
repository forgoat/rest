package com.rest.entity;

import java.math.BigInteger;

public class Question {
    private BigInteger id;
    private BigInteger klass_seminar_id;
    private BigInteger attendance_id;
    private BigInteger team_id;
    private BigInteger student_id;
    private Integer is_selected;
    private float score;

    public Question() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKlass_seminar_id() {
        return klass_seminar_id;
    }

    public void setKlass_seminar_id(BigInteger klass_seminar_id) {
        this.klass_seminar_id = klass_seminar_id;
    }

    public BigInteger getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(BigInteger attendance_id) {
        this.attendance_id = attendance_id;
    }

    public BigInteger getTeam_id() {
        return team_id;
    }

    public void setTeam_id(BigInteger team_id) {
        this.team_id = team_id;
    }

    public BigInteger getStudent_id() {
        return student_id;
    }

    public void setStudent_id(BigInteger student_id) {
        this.student_id = student_id;
    }

    public Integer getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(Integer is_selected) {
        this.is_selected = is_selected;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
