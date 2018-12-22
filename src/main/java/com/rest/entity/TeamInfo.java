package com.rest.entity;

import java.util.List;

public class TeamInfo {
    private Long teamId;
    private Long courseId;
    private Long classId;
    private Integer team_serial;
    private String team_name;
    private Student leader;
    private List<Student> member;

    public TeamInfo() {
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getTeam_serial() {
        return team_serial;
    }

    public void setTeam_serial(Integer team_serial) {
        this.team_serial = team_serial;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public List<Student> getMember() {
        return member;
    }

    public void setMember(List<Student> member) {
        this.member = member;
    }
}
