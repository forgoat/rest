package com.rest.po;

public class KlassStudent {
    private Long klassId;
    private Long studentId;
    private Long courseId;
    private Long teamId;

    public KlassStudent() {
    }

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
