package com.rest.entity;

import java.sql.Date;

public class KlassSeminarInfo {
    private Long id;
    private Long klassId;
    private Long seminarId;
    private Date reportDdl;
    private Integer status;
    private String seminarName;

    public KlassSeminarInfo() {
    }

    public KlassSeminarInfo(KlassSeminar klassSeminar){
        id=klassSeminar.getId();
        klassId=klassSeminar.getKlassId();
        seminarId=klassSeminar.getSeminarId();
        reportDdl=klassSeminar.getReportDdl();
        status=klassSeminar.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
    }

    public Date getReportDdl() {
        return reportDdl;
    }

    public void setReportDdl(Date reportDdl) {
        this.reportDdl = reportDdl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }
}
