package com.rest.entity;

import java.util.List;

public class TeamRoundScoreList {
    private Integer roundSerial;
    private List<TeamRoundScore> teamRoundScores;

    public TeamRoundScoreList() {
    }



    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public List<TeamRoundScore> getTeamRoundScores() {
        return teamRoundScores;
    }

    public void setTeamRoundScores(List<TeamRoundScore> teamRoundScores) {
        this.teamRoundScores = teamRoundScores;
    }
}
