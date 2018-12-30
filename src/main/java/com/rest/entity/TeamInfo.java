package com.rest.entity;

import com.rest.po.Student;

import java.util.List;

public class TeamInfo {
    private Long teamId;
    private Long courseId;
    private Long classId;
    private Integer teamSerial;
    private Integer klassSerial;
    private String teamName;
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

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    @Override
    public String toString() {
        return "TeamInfo{" +
                "teamId=" + teamId +
                ", courseId=" + courseId +
                ", classId=" + classId +
                ", teamSerial=" + teamSerial +
                ", klassSerial=" + klassSerial +
                ", teamName='" + teamName + '\'' +
                ", leader=" + leader +
                ", member=" + member +
                '}';
    }
}
