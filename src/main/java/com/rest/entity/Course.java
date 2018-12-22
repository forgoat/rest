package com.rest.entity;

import java.math.BigInteger;
import java.sql.Date;

public class Course {
    private Long id;
    private Long teacher_id;
    private String course_name;
    private String introducation;
    private Integer presentation_percentage;
    private Integer question_percentage;
    private Integer report_percentage;
    private Date team_start_time;
    private Date team_end_time;
    private Long team_main_course_id;
    private Long seminar_main_course_id;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public Integer getPresentation_percentage() {
        return presentation_percentage;
    }

    public void setPresentation_percentage(Integer presentation_percentage) {
        this.presentation_percentage = presentation_percentage;
    }

    public Integer getQuestion_percentage() {
        return question_percentage;
    }

    public void setQuestion_percentage(Integer question_percentage) {
        this.question_percentage = question_percentage;
    }

    public Integer getReport_percentage() {
        return report_percentage;
    }

    public void setReport_percentage(Integer report_percentage) {
        this.report_percentage = report_percentage;
    }

    public Date getTeam_start_time() {
        return team_start_time;
    }

    public void setTeam_start_time(Date team_start_time) {
        this.team_start_time = team_start_time;
    }

    public Date getTeam_end_time() {
        return team_end_time;
    }

    public void setTeam_end_time(Date team_end_time) {
        this.team_end_time = team_end_time;
    }

    public Long getTeam_main_course_id() {
        return team_main_course_id;
    }

    public void setTeam_main_course_id(Long team_main_course_id) {
        this.team_main_course_id = team_main_course_id;
    }

    public Long getSeminar_main_course_id() {
        return seminar_main_course_id;
    }

    public void setSeminar_main_course_id(Long seminar_main_course_id) {
        this.seminar_main_course_id = seminar_main_course_id;
    }
}
