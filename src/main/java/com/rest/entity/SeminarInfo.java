package com.rest.entity;

import java.util.List;

public class SeminarInfo {
    private Long roundId;
    private Long klassId;
    private Integer enrollNumber;
    private List<KlassSeminar> klassSeminarList;

    public SeminarInfo() {
    }

    public SeminarInfo(KlassRound klassRound){
        roundId=klassRound.getRoundId();
        klassId=klassRound.getKlassId();
        enrollNumber=klassRound.getEnrollNumber();
    }
    public void addSeminar(List<KlassSeminar> klassSeminars){
        klassSeminarList=klassSeminars;
    }
    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public Integer getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public List<KlassSeminar> getKlassSeminarList() {
        return klassSeminarList;
    }

    public void setKlassSeminarList(List<KlassSeminar> klassSeminarList) {
        this.klassSeminarList = klassSeminarList;
    }
}
