package com.rest.entity;

import java.util.List;

public class RoundScoreInfo {
    private List<Team> teamList;
    private List<Round> roundList;
    private List<Klass> klassList;

    public RoundScoreInfo() {
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }

    public List<Klass> getKlassList() {
        return klassList;
    }

    public void setKlassList(List<Klass> klassList) {
        this.klassList = klassList;
    }
}
