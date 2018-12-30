package com.rest.entity;

import java.util.List;

public class RoundList {
    private Long roundId;
    private Integer roundSerial;
    private List<SeminarList> seminarLists;

    public RoundList() {
    }

    public RoundList(Round round){
        roundId=round.getId();
        roundSerial=round.getRoundSerial();

    }
    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public List<SeminarList> getSeminarLists() {
        return seminarLists;
    }

    public void setSeminarLists(List<SeminarList> seminarLists) {
        this.seminarLists = seminarLists;
    }
}
