package com.rest.entity;

import com.rest.po.Attendance;

public class AttendanceInfo {
    private Long teamId;
    private Integer teamOrder;
    private Long attendanceId;
    private String pptUrl;
    private String pptName;
    private Integer teamSerial;
    private String teamName;

    public AttendanceInfo() {
    }

    public AttendanceInfo(Attendance attendance){
        teamId=attendance.getTeamId();
        attendanceId=attendance.getId();
        pptName=attendance.getPptName();
        pptUrl=attendance.getPptUrl();
        teamOrder=attendance.getTeamOrder();
    }
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Integer teamOrder) {
        this.teamOrder = teamOrder;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getPptUrl() {
        return pptUrl;
    }

    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl;
    }

    public String getPptName() {
        return pptName;
    }

    public void setPptName(String pptName) {
        this.pptName = pptName;
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

    public int compareTo(AttendanceInfo attendanceInfo){
        if (attendanceInfo.getTeamOrder()>teamOrder){
            return -1;
        }
        else {
            return 1;
        }
    }
}
