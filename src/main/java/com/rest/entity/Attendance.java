package com.rest.entity;

import java.math.BigInteger;

public class Attendance {
    private BigInteger id;
    private BigInteger klass_seminar_id;
    private BigInteger team_id;
    private Integer team_order;
    private Integer is_present;
    private String report_name;
    private String report_url;
    private String ppt_name;
    private String ppt_url;

    public Attendance() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKlass_seminar_id() {
        return klass_seminar_id;
    }

    public void setKlass_seminar_id(BigInteger klass_seminar_id) {
        this.klass_seminar_id = klass_seminar_id;
    }

    public BigInteger getTeam_id() {
        return team_id;
    }

    public void setTeam_id(BigInteger team_id) {
        this.team_id = team_id;
    }

    public Integer getTeam_order() {
        return team_order;
    }

    public void setTeam_order(Integer team_order) {
        this.team_order = team_order;
    }

    public Integer getIs_present() {
        return is_present;
    }

    public void setIs_present(Integer is_present) {
        this.is_present = is_present;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getReport_url() {
        return report_url;
    }

    public void setReport_url(String report_url) {
        this.report_url = report_url;
    }

    public String getPpt_name() {
        return ppt_name;
    }

    public void setPpt_name(String ppt_name) {
        this.ppt_name = ppt_name;
    }

    public String getPpt_url() {
        return ppt_url;
    }

    public void setPpt_url(String ppt_url) {
        this.ppt_url = ppt_url;
    }
}
