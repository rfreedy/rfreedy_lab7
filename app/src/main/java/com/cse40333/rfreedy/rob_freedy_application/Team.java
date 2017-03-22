package com.cse40333.rfreedy.rob_freedy_application;


import java.io.Serializable;

public class Team implements Serializable {
    String game_date;
    String game_location;
    String opponent_logo;
    String opponent;
    String opponent_mascot;
    String opponent_record;
    String score;
    String result;
    String nd_name;
    String nd_mascot;
    String nd_record;
    String nd_logo;
    String camera;
    String date;

    public Team (String [] info) {
        setGameDate(info[0]);
        setGameLocation(info[1]);
        setOpponentLogo(info[2]);
        setGameOpponent(info[3]);
        setOpponentMascot(info[4]);
        setOpponentRecord(info[5]);
        setGameScore(info[6]);
        setGameResult(info[7]);
        setNDName(info[8]);
        setNDLogo(info[11]);
        setNDMascot(info[9]);
        setNDRecord(info[10]);
        setGameCamera(info[12]);
        setDate(info[13]);
    }

    public void setGameDate(String a) {
        this.game_date = a;
    }

    public String getGameDate() {
        return this.game_date;
    }

    public void setGameLocation(String b) {
        this.game_date = b;
    }

    public String getGameLocation() {
        return this.game_location;
    }

    public void setOpponentLogo(String c) {
        this.opponent_logo = c;
    }

    public String getOpponentLogo() {
        return this.opponent_logo;
    }

    public void setGameOpponent(String d) {
        this.opponent = d;
    }

    public String getGameOpponent() {
        return this.opponent;
    }

    public void setOpponentMascot(String e) {
        this.opponent_mascot = e;
    }

    public String getOpponentMascot() {
        return this.opponent_mascot;
    }

    public void setOpponentRecord(String f) {
        this.opponent_record = f;
    }

    public String getOpponentRecord() {
        return this.opponent_record;
    }

    public void setGameScore(String g) {
        this.score = g;
    }

    public String getGameScore() {
        return this.score;
    }

    public void setGameResult(String h) {
        this.result = h;
    }

    public String getGameResult() {
        return this.result;
    }

    public void setNDName(String i) {
        this.nd_name = i;
    }

    public String getNDName() {
        return this.nd_name;
    }

    public void setNDLogo(String j) {
        this.nd_logo = j;
    }

    public String getNDLogo() {
        return this.nd_logo;
    }

    public void setNDMascot(String k) {
        this.nd_mascot = k;
    }

    public String getNDMascot() {
        return this.nd_mascot;
    }

    public void setNDRecord(String l) {
        this.nd_record = l;
    }

    public String getNDRecord() {
        return this.nd_record;
    }

    public void setGameCamera(String m) {
        this.camera = m;
    }

    public String getGameCamera() {
        return this.camera;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

}
