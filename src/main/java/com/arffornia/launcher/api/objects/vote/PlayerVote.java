package com.arffornia.launcher.api.objects.vote;

public class PlayerVote {
    private String playerName;
    private String uuid;
    private int voteCount;

    public PlayerVote(String playerName, String uuid, int voteCount) {
        this.playerName = playerName;
        this.uuid = uuid;
        this.voteCount = voteCount;
    }

    public String toString() {
        return String.format("playerName: %s, voteCount: %d", playerName, voteCount);
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getUuid() {
        return uuid;
    }
}
