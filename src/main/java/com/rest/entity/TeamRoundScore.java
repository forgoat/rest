package com.rest.entity;

import java.util.List;

public class TeamRoundScore {
    private Double roundScore;
    private Long teamId;
    private Integer teamSerial;
    private Integer klassSerial;
    private List<SeminarScoreInfo> seminarScoreInfos;

    public TeamRoundScore() {
    }

    public Double getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(Double roundScore) {
        this.roundScore = roundScore;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    public List<SeminarScoreInfo> getSeminarScoreInfos() {
        return seminarScoreInfos;
    }

    public void setSeminarScoreInfos(List<SeminarScoreInfo> seminarScoreInfos) {
        this.seminarScoreInfos = seminarScoreInfos;
    }
}
