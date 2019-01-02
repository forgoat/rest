package com.rest.entity;

public class SeminarScoreInfo {
    private Long klassSeminarId;
    private String SeminarName;
    private Double presentationScore;
    private Double reportScore;
    private Double questionScore;

    public SeminarScoreInfo() {
    }

    public SeminarScoreInfo(SeminarScore seminarScore){
        klassSeminarId=seminarScore.getKlassSeminarId();
        presentationScore=seminarScore.getPresentationScore();
        reportScore=seminarScore.getReportScore();
        questionScore=seminarScore.getQuestionScore();
    }
    public Long getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(Long klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public String getSeminarName() {
        return SeminarName;
    }

    public void setSeminarName(String seminarName) {
        SeminarName = seminarName;
    }

    public Double getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(Double presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Double getReportScore() {
        return reportScore;
    }

    public void setReportScore(Double reportScore) {
        this.reportScore = reportScore;
    }

    public Double getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Double questionScore) {
        this.questionScore = questionScore;
    }
}
