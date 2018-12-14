package com.rest.entity;

import java.math.BigInteger;

public class Team {
    private BigInteger id;
    private BigInteger klass_id;
    private BigInteger course_id;
    private BigInteger leader_id;
    private String team_name;
    private Integer team_serial;
    private Integer status;

    public Team() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKlass_id() {
        return klass_id;
    }

    public void setKlass_id(BigInteger klass_id) {
        this.klass_id = klass_id;
    }

    public BigInteger getCourse_id() {
        return course_id;
    }

    public void setCourse_id(BigInteger course_id) {
        this.course_id = course_id;
    }

    public BigInteger getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(BigInteger leader_id) {
        this.leader_id = leader_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Integer getTeam_serial() {
        return team_serial;
    }

    public void setTeam_serial(Integer team_serial) {
        this.team_serial = team_serial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
