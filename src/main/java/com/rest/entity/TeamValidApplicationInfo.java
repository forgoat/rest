package com.rest.entity;

public class TeamValidApplicationInfo {
    private Long id;
    private Long teamId;
    private Long teacherId;
    private String reason;
    private Integer status;
    private String courseName;
    private String leaderName;

    public TeamValidApplicationInfo() {
    }

    public TeamValidApplicationInfo(TeamValidApplication teamValidApplication){
        id=teamValidApplication.getId();
        teamId=teamValidApplication.getTeamId();
        teacherId=teamValidApplication.getTeacherId();
        reason=teamValidApplication.getReason();
        status=teamValidApplication.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
