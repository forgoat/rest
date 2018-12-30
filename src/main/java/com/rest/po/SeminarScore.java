package com.rest.po;

public class SeminarScore {
    private Long klassSeminarId;
    private Long teamId;
    private Double totalScore;
    private Double presentationScore;
    private Double questionScore;
    private Double reportScore;

    public SeminarScore() {
    }

    public Long getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(Long klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(Double presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Double getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Double questionScore) {
        this.questionScore = questionScore;
    }

    public Double getReportScore() {
        return reportScore;
    }

    public void setReportScore(Double reportScore) {
        this.reportScore = reportScore;
    }

    @Override
    public String toString() {
        return "SeminarScore{" +
                "klassSeminarId=" + klassSeminarId +
                ", teamId=" + teamId +
                ", totalScore=" + totalScore +
                ", presentationScore=" + presentationScore +
                ", questionScore=" + questionScore +
                ", reportScore=" + reportScore +
                '}';
    }
}
