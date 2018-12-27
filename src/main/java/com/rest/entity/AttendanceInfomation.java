package com.rest.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AttendanceInfomation {
    private List<AttendanceInfo> attendanceInfoList;
    private Integer klassSerial;
    private Integer maxTeam;

    public AttendanceInfomation() {
    }

    public AttendanceInfomation(List<AttendanceInfo> attendanceInfos){
        Collections.sort(attendanceInfos, new Comparator<AttendanceInfo>() {
            @Override
            public int compare(AttendanceInfo o1, AttendanceInfo o2) {
                if (o1.getTeamOrder()>o2.getTeamOrder()){
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        attendanceInfoList=attendanceInfos;
    }
    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    public Integer getMaxTeam() {
        return maxTeam;
    }

    public void setMaxTeam(Integer maxTeam) {
        this.maxTeam = maxTeam;
    }

    public List<AttendanceInfo> getAttendanceInfoList() {
        return attendanceInfoList;
    }

    public void setAttendanceInfoList(List<AttendanceInfo> attendanceInfoList) {
        this.attendanceInfoList = attendanceInfoList;
    }
}
