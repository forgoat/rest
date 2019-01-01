package com.rest.entity;

import java.util.List;

public class RoundScorePage {
    private Long roundId;
    private Integer roundSerial;
    private List<TeamScore> teamScoreList;

    public RoundScorePage() {
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

    public List<TeamScore> getTeamScoreList() {
        return teamScoreList;
    }

    public void setTeamScoreList(List<TeamScore> teamScoreList) {
        this.teamScoreList = teamScoreList;
    }

    @Override
    public String toString() {
        return "RoundScorePage{" +
                "roundId=" + roundId +
                ", roundSerial=" + roundSerial +
                ", teamScoreList=" + teamScoreList +
                '}';
    }
}
