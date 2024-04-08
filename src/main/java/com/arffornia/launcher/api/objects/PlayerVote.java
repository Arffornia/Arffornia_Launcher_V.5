package com.arffornia.launcher.api.objects;

public class PlayerVote {
    private String playerName;
    private int voteCount;

    public PlayerVote(String playerName, int voteCount) {
        this.playerName = playerName;
        this.voteCount = voteCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
