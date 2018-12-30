package com.rest.entity;

import java.util.List;

public class SeminarList {
    private Long seminarId;
    private String seminarName;
    private List<KlassSeminar> klassSeminarList;

    public SeminarList() {
    }

    public SeminarList(Seminar seminar){
        seminarId=seminar.getId();
        seminarName=seminar.getSeminarName();
    }
    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public List<KlassSeminar> getKlassSeminarList() {
        return klassSeminarList;
    }

    public void setKlassSeminarList(List<KlassSeminar> klassSeminarList) {
        this.klassSeminarList = klassSeminarList;
    }
}
