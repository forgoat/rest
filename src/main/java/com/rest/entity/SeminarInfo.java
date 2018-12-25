package com.rest.entity;

import java.util.List;

public class SeminarInfo {
    private Long roundId;
    private Integer roundSerial;
    private Long klassId;
    private Integer enrollNumber;
    private Integer presentationScoreMethod;
    private Integer reportScoreMethod;
    private Integer questionScoreMethod;
    private List<KlassSeminarInfo> klassSeminarInfoList;

    public SeminarInfo() {
    }

    public SeminarInfo(Round round){
        roundId=round.getId();
        roundSerial=round.getRoundSerial();
        presentationScoreMethod=round.getPresentationScoreMethod();
        reportScoreMethod=round.getReportScoreMethod();
        questionScoreMethod=round.getQuestionScoreMethod();
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

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public List<KlassSeminarInfo> getKlassSeminarInfoList() {
        return klassSeminarInfoList;
    }

    public void setKlassSeminarInfoList(List<KlassSeminarInfo> klassSeminarInfoList) {
        this.klassSeminarInfoList = klassSeminarInfoList;
    }

    public Integer getPresentationScoreMethod() {
        return presentationScoreMethod;
    }

    public void setPresentationScoreMethod(Integer presentationScoreMethod) {
        this.presentationScoreMethod = presentationScoreMethod;
    }

    public Integer getReportScoreMethod() {
        return reportScoreMethod;
    }

    public void setReportScoreMethod(Integer reportScoreMethod) {
        this.reportScoreMethod = reportScoreMethod;
    }

    public Integer getQuestionScoreMethod() {
        return questionScoreMethod;
    }

    public void setQuestionScoreMethod(Integer questionScoreMethod) {
        this.questionScoreMethod = questionScoreMethod;
    }
}
