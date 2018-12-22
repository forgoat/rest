package com.rest.entity;

public class KlassStudent {
    private Long klass_id;
    private Long student_id;
    private Long course_id;
    private Long team_id;

    public KlassStudent() {
    }

    public Long getKlass_id() {
        return klass_id;
    }

    public void setKlass_id(Long klass_id) {
        this.klass_id = klass_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }
}
