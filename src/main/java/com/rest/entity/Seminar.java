package com.rest.entity;

import java.math.BigInteger;
import java.sql.Date;

public class Seminar {
    private BigInteger id;
    private BigInteger course_id;
    private BigInteger round_id;
    private String seminar_name;
    private String introducation;
    private Integer max_team;
    private Integer is_visible;
    private Integer seminar_serial;
    private Date enroll_start_time;
    private Date enroll_end_time;

    public Seminar() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCourse_id() {
        return course_id;
    }

    public void setCourse_id(BigInteger course_id) {
        this.course_id = course_id;
    }

    public BigInteger getRound_id() {
        return round_id;
    }

    public void setRound_id(BigInteger round_id) {
        this.round_id = round_id;
    }

    public String getSeminar_name() {
        return seminar_name;
    }

    public void setSeminar_name(String seminar_name) {
        this.seminar_name = seminar_name;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public Integer getMax_team() {
        return max_team;
    }

    public void setMax_team(Integer max_team) {
        this.max_team = max_team;
    }

    public Integer getIs_visible() {
        return is_visible;
    }

    public void setIs_visible(Integer is_visible) {
        this.is_visible = is_visible;
    }

    public Integer getSeminar_serial() {
        return seminar_serial;
    }

    public void setSeminar_serial(Integer seminar_serial) {
        this.seminar_serial = seminar_serial;
    }

    public Date getEnroll_start_time() {
        return enroll_start_time;
    }

    public void setEnroll_start_time(Date enroll_start_time) {
        this.enroll_start_time = enroll_start_time;
    }

    public Date getEnroll_end_time() {
        return enroll_end_time;
    }

    public void setEnroll_end_time(Date enroll_end_time) {
        this.enroll_end_time = enroll_end_time;
    }
}
